package com.jeeplus.core.errors;


import org.apache.commons.lang3.StringUtils;

public final class ErrorConstants {

    public static final String LOGIN_ERROR_NOT_LOGIN_IN = "您尚未登录，请登录后操作!";
    public static final String LOGIN_ERROR_FORBIDDEN = "该帐号已经被禁止登录！";
    public static final String LOGIN_ERROR_INCORRECT = "您输入的账号或者密码有误,请重新输入!";
    public static final String LOGIN_ERROR_NOTFOUND = "您输入的账号或者密码有误,请重新输入!";
    public static final String LOGIN_ERROR_EXPIRED = "您的登录已过期，请重新登录！";
    public static final String LOGIN_ERROR_FORBID_LOGGED_IN_ELSEWHERE = "您的账号已在其它地方登录，您被禁止登录！";
    public static final String LOGIN_ERROR__KICK_OUT_LOGGED_IN_ELSEWHERE = "您的账号在另一台设备上登录,如非本人操作，请立即修改密码！";
    public static final String LOGIN_ERROR_ERROR_VALIDATE_CODE = "您输入的验证码不正确，请重新输入！";
    public static final String LOGIN_ERROR_MAX_COUNT = "您的密码输入错误已超过5次,请20分钟后重试！";

    public static final String ERROR_PARAM_ERROR = "您的参数已经遭到完整性破坏,请重新操作！";

    public static boolean errFiler(String errMsg){
        if(StringUtils.isNotBlank(errMsg)&&(errMsg.equals(LOGIN_ERROR_ERROR_VALIDATE_CODE)||
                errMsg.equals(LOGIN_ERROR__KICK_OUT_LOGGED_IN_ELSEWHERE)||
                errMsg.equals(LOGIN_ERROR_FORBID_LOGGED_IN_ELSEWHERE)||
                errMsg.equals(LOGIN_ERROR_EXPIRED)||
                errMsg.equals(LOGIN_ERROR_NOTFOUND)||
                errMsg.equals(LOGIN_ERROR_INCORRECT)||
                errMsg.equals(LOGIN_ERROR_FORBIDDEN)||
                errMsg.equals(LOGIN_ERROR_MAX_COUNT)||
                errMsg.equals(ERROR_PARAM_ERROR)||
                errMsg.equals(LOGIN_ERROR_NOT_LOGIN_IN))){
            return true;
        }else{
            return false;
        }
    }

}
