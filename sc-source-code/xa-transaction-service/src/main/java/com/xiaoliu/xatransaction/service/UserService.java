package com.xiaoliu.xatransaction.service;


import com.xiaoliu.xatransaction.entity.User;

/**
 * @description: 用户管理Service
 * @author: liufb
 * @create: 2020/8/5 9:22
 **/
public interface UserService {
    /**
     * 保存用户
     *
     * @param user 用户对象
     */
    void save(User user);
}
