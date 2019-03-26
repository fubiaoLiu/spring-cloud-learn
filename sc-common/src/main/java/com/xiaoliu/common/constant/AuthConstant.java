package com.xiaoliu.common.constant;

/**
 * @description: 权限常量类
 * @author: FubiaoLiu
 * @date: 2018/9/27
 */
public class AuthConstant {
    /**
     * URL version
     */
    public static final String URL_VERSION = "/v2";
    /**
     * 斜杠
     */
    public static final String SLASH = "/";
    /**
     * 接口BaseUrl地址
     */
    public static final String BASE_URL = "/api" + URL_VERSION;
    /**
     * token加盐
     */
    public static final String SIGNING_KEY = "^#Lfb-@xltx!";
    /**
     * token过期时间
     */
    public static final int TOKEN_EXPIRATION_TIME_ = 60 * 60 * 1000;
    /**
     * 登录用户名参数名
     */
    public static final String PARAM_USERNAME = "username";
    /**
     * 登录密码参数名
     */
    public static final String PARAM_PASSWORD = "password";
    /**
     * 登录接口地址
     */
    public static final String URL_LOGIN = "/auth/authentication/form";
    /**
     * 登出接口地址
     */
    public static final String URL_LOGOUT = "/auth/logout";
    /**
     * swagger2 文档路径
     */
    public static final String SWAGGER2_API = "/v2/api-docs";
    /**
     * 角色登录权限，只要登录就能访问的权限
     */
    public static final String AUTH_ROLE_LOGIN = "ROLE_LOGIN";
    /**
     * 角色登录权限，只要登录就能访问的权限
     */
    public static final String AUTH_HTTP_REQUEST_BASIC = "Basic ";

    /**
     * token的key前缀
     */
    public static final String TOKEN_PRE = "access:";
    //---------------------   网关-放行路径  -----------------------

    public static final String[] URL_AUTH_PASS = {
            URL_LOGIN,
            URL_LOGOUT
    };

}
