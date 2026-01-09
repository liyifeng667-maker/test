package com.jeeplus.core.errors;

import com.jeeplus.aop.demo.exception.DemoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ExceptionTranslator {


    /**
     * 处理未知异常
     */
    @ExceptionHandler({Throwable.class, java.sql.SQLException.class, java.io.IOException.class})
    public ResponseEntity handleException(Throwable e) {
        log.error("{}", e.getMessage());
        String errMsg = e.getMessage();
        if (StringUtils.isNotBlank(errMsg) && ErrorConstants.errFiler(errMsg)) {

        } else if (StringUtils.isNotBlank(errMsg) && e.getMessage().equals("IllegalBlockSizeException: Input length must be multiple of 16 when decrypting with padded cipher")) {

            errMsg = "错误:参数格式错误!";

        } else {
            errMsg = "错误:系统异常请联系管理员处理!";
        }
        e.printStackTrace();
        return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 演示模式，禁止操作
     */
    @ExceptionHandler({DemoException.class})
    public ResponseEntity handleDemoException(Throwable e) {
        log.error("{}", e.getMessage());
        String errMsg = e.getMessage();
        if (StringUtils.isNotBlank(errMsg) && ErrorConstants.errFiler(errMsg)) {

        } else {
            errMsg = "错误:登录过期，或者用户名密码错误!";
        }
        return new ResponseEntity(errMsg, HttpStatus.FORBIDDEN);
    }


    /**
     * 没有访问资源的权限
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleUnauthorizedException(Throwable e) {
        log.error("{}", e.getMessage());
        String errMsg = "权限不足:" + e.getMessage();
        errMsg = "错误:权限不足!";
        return new ResponseEntity(errMsg, HttpStatus.FORBIDDEN);
    }

    /**
     * 登录过期，或者用户名密码错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(Throwable e) {
        log.error("{}", e.getMessage());
        String errMsg = e.getMessage();
        if (ErrorConstants.errFiler(errMsg)) {

        } else {
            errMsg = ErrorConstants.LOGIN_ERROR_INCORRECT;
        }

        return new ResponseEntity(errMsg, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 请求参数非法
     */
    @ExceptionHandler({
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity handleBadRequestException(Throwable e) {
        log.error("{}", e.getMessage());
        String errMsg = "您提交的参数，服务器无法处理";
        return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", e.getMessage());
        // 打印堆栈信息
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        String[] str = Objects.requireNonNull(error.getCodes())[1].split("\\.");
//        String errMsg = "参数" + str[1] + ", 数据验证失败:" + error.getDefaultMessage ( );
        String errMsg = "您提交的参数，服务器无法处理";

        return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity handleBindException(BindException e) {
        log.error("{}", e.getMessage());
        // 打印堆栈信息
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        String[] str = Objects.requireNonNull(error.getCodes())[1].split("\\.");
//        String errMsg = "参数" + str[1] + ", 数据验证失败:" + error.getDefaultMessage ( );
        String errMsg = "您提交的参数，服务器无法处理";
        return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
    }
}
