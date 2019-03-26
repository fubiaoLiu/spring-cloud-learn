package com.xiaoliu.common.constant;

/**
 * @description:
 * @author: yk.tan
 * @since: 2017/6/7
 * @history:
 */
public class CacheConstant {

    //===========缓存key前缀==============================
    //-------------------------------------------------

    /**
     * 缓存key前缀｛分隔符｝
     */
    public static final String DELIMITER_CACHE = ":";

    /**
     * 用户信息：缓存key前缀
     */
    public static final String USER_CACHE = "user";

    /**
     * token：缓存key前缀
     */
    public static final String TOKEN_CACHE = "token";

    public static final String USERNAME_CACHE = "username";

    public static final String ACCESSTOKEN_CACHE = "access_token";

    public static final String REFRESHTOKEN_CACHE = "refresh_token";
    //-------------------------------------------------
    //===========缓存key前缀==============================

    /**
     * 盐
     */
    public static final String SALT = "lfb^#(&(@)%%#xltx#@@$";


    /**
     * （用户登录信息）缓存数据过期时间单位秒
     */
    public static final long LOGIN_INFO_TIMEOUT = 1800;
    /**
     * Excel导入数据缓存过期时间（单位秒）
     */
    public static final long UPLOAD_DATA_TIMEOUT = 43200;


}
