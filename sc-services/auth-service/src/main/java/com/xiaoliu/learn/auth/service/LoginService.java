package com.xiaoliu.learn.auth.service;

import com.xiaoliu.learn.auth.domain.User;

/**
 * @description: 登录Service
 * @author: liufb
 * @create: 2020/5/18 16:12
 **/
public interface LoginService {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByName(String username);
}
