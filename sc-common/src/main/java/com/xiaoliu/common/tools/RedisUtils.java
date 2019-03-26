package com.xiaoliu.common.tools;

import com.xiaoliu.common.constant.CacheConstant;
import com.xiaoliu.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author leyangjie
 * @date 2018/11/26 14:49
 * 操作redis 工具类
 */
@Component
public class RedisUtils {

    /**
     * 默认过期时间
     */
    private static final int EXPIRE_TIME = Constant.LOGIN_EXPIRE_TIME;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 将用户添加缓存
     */
    /*public void setUserCache(String username, Long roleId, User user, Integer expireTime) {
        if (expireTime == null || expireTime == 0) {
            //使用默认过期时间
            expireTime = EXPIRE_TIME;
        }
        redisTemplate.opsForValue().set(CacheConstant.USER_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, user, expireTime, TimeUnit.SECONDS);
    }*/

    /**
     * 从缓存中获取用户信息
     *
     * @param username
     * @return
     */
    /*public User getUserCache(String username, Long roleId) {
        return (User) redisTemplate.opsForValue().get(CacheConstant.USER_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
    }*/

    /**
     * 刷新用户的过期时间
     *
     * @param username 用户名
     * @param roleId   角色id
     */
    public void expireUserCache(String username, Long roleId) {
        redisTemplate.expire(CacheConstant.USER_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, EXPIRE_TIME, TimeUnit.SECONDS);
    }


    /**
     * 将用户名添加到redis 目的：为了刷新access_token
     *
     * @param username   用户名
     * @param roleId     角色id
     * @param expireTime 过期时间
     */
    public void setUsernameCache(String username, Long roleId, Integer expireTime) {
        if (expireTime == null || expireTime == 0) {
            //使用默认过期时间
            expireTime = EXPIRE_TIME;
        }
        redisTemplate.opsForValue().set(CacheConstant.USERNAME_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, username, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取用户名 目的：为了刷新access_token
     *
     * @param username 用户名
     * @param roleId   角色id
     * @return
     */
    public String getUsernameCache(String username, Long roleId) {
        return (String) redisTemplate.opsForValue().get(CacheConstant.USERNAME_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
    }

    /**
     * 刷新用户的过期时间
     *
     * @param username
     * @param roleId
     */
    public void expireUsernameCache(String username, Long roleId) {
        redisTemplate.expire(CacheConstant.USERNAME_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 设置用户的 access_token
     *
     * @param username    用户名
     * @param accessToken oauth 生成的token
     */
    public void setAccessTokenCache(String username, Long roleId, String accessToken, Integer expireTime) {
        if (expireTime == null || expireTime == 0) {
            //使用默认过期时间
            expireTime = EXPIRE_TIME;
        }
        redisTemplate.opsForValue().set(CacheConstant.ACCESSTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, accessToken, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取用户的access_token
     *
     * @param username 用户名
     * @return
     */
    public String getAccessTokenCache(String username, Long roleId) {
        try {
            return (String) redisTemplate.opsForValue().get(CacheConstant.ACCESSTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void expireAccessTokenCache(String username, Long roleId) {
        redisTemplate.expire(CacheConstant.ACCESSTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 设置用户的refresh_token
     *
     * @param username     用户名
     * @param refreshToken oauth2返回的刷新token
     * @param expireTime   过期时间
     */
    public void setRefreshTokenCache(String username, Long roleId, String refreshToken, Integer expireTime) {
        if (expireTime == null || expireTime == 0) {
            //refresh_token 默认设置一周
            expireTime = Constant.REFRESH_TOKEN_EXPIRE_TIME;
        }
        redisTemplate.opsForValue().set(CacheConstant.REFRESHTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT, refreshToken, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取用户的refresh_token
     *
     * @param username 用户名
     * @return refresh_token
     */
    public String getRefreshTokenCache(String username, Long roleId) {
        return (String) redisTemplate.opsForValue().get(CacheConstant.REFRESHTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
    }

    /**
     * 销毁用户信息
     *
     * @param username
     * @param roleId
     */
    public void destroyUserInfoCache(String username, Long roleId) {
        Set<String> keys = new HashSet<>();
        //用户缓存key
        keys.add(CacheConstant.USER_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
        //用户名缓存 key
        keys.add(CacheConstant.USERNAME_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
        //access_token 缓存key
        keys.add(CacheConstant.ACCESSTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
        keys.add(CacheConstant.REFRESHTOKEN_CACHE + CacheConstant.DELIMITER_CACHE + username + CacheConstant.DELIMITER_CACHE + roleId + CacheConstant.SALT);
        redisTemplate.delete(keys);
    }

    /**
     * 根据用户名、角色id获取用户功能权限
     *
     * @param username
     * @param roleId
     * @return
     */
    /*public List<FuncPermission> getUserFuncPermCache(String username, Long roleId) {
        User user = getUserCache(username, roleId);
        if (null == user) {
            return null;
        }
        return user.getFuncPermissionsList();
    }*/

    /**
     * 根据用户名、角色id获取用户数据权限
     *
     * @param username
     * @param roleId
     * @return
     */
    /*public List<DataPermission> getUserDataPermCache(String username, Long roleId) {
        User user = getUserCache(username, roleId);
        if (null == user) {
            return null;
        }
        return user.getDataPermissionList();
    }*/


}
