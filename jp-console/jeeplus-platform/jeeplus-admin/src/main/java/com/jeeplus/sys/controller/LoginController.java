/**
 * Copyright &copy; 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jeeplus.aop.logging.annotation.ApiLog;
import com.jeeplus.common.redis.RedisUtils;
import com.jeeplus.common.utils.RequestUtils;
import com.jeeplus.common.utils.ResponseUtil;
import com.jeeplus.config.properties.JeePlusProperties;
import com.jeeplus.core.errors.ErrorConstants;
import com.jeeplus.security.jwt.TokenProvider;
import com.jeeplus.security.util.SecurityUtils;
import com.jeeplus.sys.constant.CacheNames;
import com.jeeplus.sys.constant.CommonConstants;
import com.jeeplus.sys.constant.enums.LogTypeEnum;
import com.jeeplus.sys.model.LoginForm;
import com.jeeplus.sys.service.UserService;
import com.jeeplus.sys.service.dto.UserDTO;
import com.jeeplus.sys.utils.AESUtil;
import com.jeeplus.sys.utils.RSAUtils;
import com.jeeplus.sys.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.UUID;

/**
 * 登录Controller
 *
 * @author jeeplus
 * @version 2021-5-31
 */
@Slf4j
@RestController
@Api(tags = "登录管理")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登录
     *
     * @param loginForm
     * @return
     */
    @PostMapping("/sys/login")
    @ApiLog(value = "用户登录", type = LogTypeEnum.LOGIN)
    @ApiOperation("登录接口")
    public ResponseEntity login(@RequestBody LoginForm loginForm) throws UnsupportedEncodingException {
        ResponseUtil responseUtil = new ResponseUtil ( );
        String aesParam = loginForm.getAesParam();
        JSONObject obj = new JSONObject();

        String privateKey = RedisUtils.getInstance ().get (CacheNames.SYS_RSA_KEY, loginForm.getUuid() ).toString();
        String jsonStr = RSAUtils.privateDecrypt(aesParam,privateKey);
        jsonStr = URLDecoder.decode(jsonStr,"UTF-8");
        obj = JSONUtil.parseObj(jsonStr);
        String username =obj.getStr("username");
        String password = obj.getStr("password");
        String code = obj.getStr("code");
        String uuid = obj.getStr("uuid");

        UserDTO userDTO = userService.getUserByLoginName2(username);
        // 密码错误输入超过5次禁止登录
        if(userDTO!=null && StringUtils.isNotBlank(userDTO.getId())){
            if(userDTO.getLoginErrNum()!=null&& userDTO.getLoginErrNum()>=5){
                throw new AccountExpiredException ( ErrorConstants.LOGIN_ERROR_MAX_COUNT );
            }
        }
        //TODO 登录验证码验证
//        if ( !code.equals ( RedisUtils.getInstance ( ).get ( CacheNames.SYS_CACHE_CODE, uuid ) ) ) {
//            //验证失败后清除验证码
//            RedisUtils.getInstance ( ).delete ( CacheNames.SYS_CACHE_CODE, uuid);
//            RedisUtils.getInstance ( ).delete ( CacheNames.SYS_RSA_KEY, uuid);
//            throw new AccountExpiredException ( ErrorConstants.LOGIN_ERROR_ERROR_VALIDATE_CODE );
//        }
        RedisUtils.getInstance ( ).delete ( CacheNames.SYS_CACHE_CODE, uuid);
        RedisUtils.getInstance ( ).delete ( CacheNames.SYS_RSA_KEY, uuid);
        try {
            SecurityUtils.login ( username, password, authenticationManager ); //登录操作spring security
        }catch (BadCredentialsException e){
            //密码错误
            if(userDTO!=null && StringUtils.isNotBlank(userDTO.getId())){
                Integer loginErrNum = userDTO.getLoginErrNum();
                if(loginErrNum==null){
                    loginErrNum = 1;
                }else{
                    loginErrNum+=1;
                }
                userDTO.setLoginErrNum(loginErrNum);
                userService.saveOrUpdate(userDTO);
            }
            throw new BadCredentialsException(e.getMessage());
        }


        /**
         * 单一登录判断
         */
        if ( !userService.isEnableLogin ( username ) ) {
            throw new DisabledException ( ErrorConstants.LOGIN_ERROR_FORBID_LOGGED_IN_ELSEWHERE );
        }

        //登录成功，生成token

        String token = TokenProvider.createAccessToken ( username, userDTO.getPassword ( ) );




        responseUtil.add ( TokenProvider.TOKEN, token );
        userDTO.setLoginErrNum(0);
        //更新登录信息
        updateUserLoginInfo ( responseUtil, userDTO, token );

        return responseUtil.ok ();
    }


    /**
     * cas登录
     * vue 传递ticket参数验证，并返回token
     */
    @ApiLog(value = "单点登录", type = LogTypeEnum.ACCESS)
    @RequestMapping("/sys/casLogin")
    public ResponseEntity casLogin(@RequestParam(name = "ticket") String ticket,
                                   @RequestParam(name = "service") String service, @Value("${cas.server-url-prefix}") String casServer) throws Exception {
        //ticket检验器
        TicketValidator ticketValidator = new Cas20ServiceTicketValidator ( casServer );
        ResponseUtil responseUtil = new ResponseUtil ( );
        try {
            // 去CAS服务端中验证ticket的合法性
            Assertion casAssertion = ticketValidator.validate ( ticket, service );
            // 从CAS服务端中获取相关属性,包括用户名、是否设置RememberMe等
            AttributePrincipal casPrincipal = casAssertion.getPrincipal ( );
            String loginName = casPrincipal.getName ( );
            // 校验用户名密码
            UserDTO userDTO = UserUtils.getByLoginName ( loginName );
            if ( userDTO != null ) {
                if ( CommonConstants.NO.equals ( userDTO.getLoginFlag ( ) ) ) {

                    throw new LockedException ( ErrorConstants.LOGIN_ERROR_FORBIDDEN );
                }
                // 单点登录实现不需要校验用户名密码
//                SecurityUtils.login (userDTO.getLoginName (), userDTO.getPassword (), authenticationManager  );
                String token = TokenProvider.createAccessToken ( userDTO.getLoginName ( ), userDTO.getPassword ( ) );
                Authentication authentication = TokenProvider.getAuthentication ( token );
                SecurityContextHolder.getContext ( ).setAuthentication ( authentication );
                responseUtil.add ( TokenProvider.TOKEN, token );
                // 更新登录信息
                updateUserLoginInfo ( responseUtil, userDTO, token );

                return responseUtil.ok ( );
            } else {
                AuthenticationException e = new UsernameNotFoundException ( ErrorConstants.LOGIN_ERROR_NOTFOUND );
                log.error ( "用户【loginName:" + loginName + "】不存在!", e );
                throw e;
            }
        } catch (TicketValidationException e) {
            log.error ( "Unable to validate ticket [" + ticket + "]", e );
            throw new CredentialsExpiredException ( "未通过验证的ticket [" + ticket + "]", e );
        }

    }


    private void updateUserLoginInfo(ResponseUtil responseUtil, UserDTO userDTO, String token) {

        //获取秘钥
        com.alibaba.fastjson2.JSONObject keyObj =  RSAUtils.getkey();
        //将私钥存进缓存
        redisUtils.set ( CacheNames.SYS_RSA_KEY, token, keyObj.getString("privateKey") );
        redisUtils.expire ( CacheNames.SYS_RSA_KEY, token, JeePlusProperties.newInstance ( ).getEXPIRE_TIME ( ) );
        responseUtil.add("publicKey",keyObj.getString("publicKey"));
        String username = userDTO.getLoginName ( );
        redisUtils.set ( userDTO.getId(), token );
        redisUtils.set ( CacheNames.USER_CACHE_TOKEN + username + ":" + token, token );
        redisUtils.expire ( CacheNames.USER_CACHE_TOKEN + username + ":" + token, JeePlusProperties.newInstance ( ).getEXPIRE_TIME ( ) );
        responseUtil.add ( "oldLoginDate", userDTO.getLoginDate ( ) );
        responseUtil.add ( "oldLoginIp", userDTO.getLoginIp ( ) );
        //更新登录日期
        userDTO.setLoginDate ( new Date ( ) );
        userDTO.setLoginIp ( ServletUtil.getClientIP ( RequestUtils.getRequest ( ) ) );
        userService.updateUserLoginInfo ( userDTO );

    }


    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("退出登录")
    @ApiLog(value = "退出登录", type = LogTypeEnum.LOGIN)
    @GetMapping("/sys/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityUtils.getAuthentication ( );
        ResponseUtil responseUtil = new ResponseUtil ( );
        if ( auth != null ) {
            UserUtils.deleteCache ( UserUtils.getCurrentUserDTO ( ) );
            String token = TokenProvider.resolveToken ( request );
            redisUtils.delete ( CacheNames.USER_CACHE_TOKEN + TokenProvider.getLoginName ( token ) + ":" + token );
            redisUtils.delete ( CacheNames.SYS_RSA_KEY, token );
            new SecurityContextLogoutHandler ( ).logout ( request, response, auth );
        }
        responseUtil.add("mes","退出成功");
        return responseUtil.ok();
    }


    /**
     * 获取登陆验证码
     *
     * @throws
     */
    @ApiOperation("获取验证码")
    @ApiLog("获取验证码")
    @GetMapping("/sys/getCode")
    public ResponseEntity getCode() {
        //HuTool定义图形验证码的长和宽,验证码的位数，干扰线的条数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha ( 116, 36, 4, 50 );
        String uuid = UUID.randomUUID ( ).toString ( );
        //将验证码放入session
        RedisUtils.getInstance ( ).set ( CacheNames.SYS_CACHE_CODE, uuid, lineCaptcha.getCode ( ) );
        RedisUtils.getInstance ( ).expire ( CacheNames.SYS_CACHE_CODE, uuid, 60 * 5 );
        //获取秘钥
        com.alibaba.fastjson2.JSONObject keyObj =  RSAUtils.getkey();
        //将私钥存进缓存
        RedisUtils.getInstance ( ).set ( CacheNames.SYS_RSA_KEY, uuid, keyObj.getString("privateKey") );
        RedisUtils.getInstance ( ).expire ( CacheNames.SYS_RSA_KEY, uuid, 60 * 5 );
        return ResponseUtil.newInstance ( ).add ( "codeImg", lineCaptcha.getImageBase64 ( ) ).add ( "uuid", uuid ).add ( "publicKey", keyObj.getString("publicKey") ).ok ( );
    }


}
