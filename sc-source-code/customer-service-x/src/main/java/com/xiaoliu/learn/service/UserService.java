package com.xiaoliu.learn.service;

import com.xiaoliu.common.entity.User;

import java.util.concurrent.Future;

/**
 * @description: 用户管理Service
 * @author: liufb
 * @create: 2020/8/5 9:22
 **/
public interface UserService {
    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 用户信息
     */
    User getById(Long id);

    /**
     * 保存用户
     *
     * @param user 用户对象
     */
    void save(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    void update(User user);

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 根据ID查询（测试请求合并）
     *
     * @param id ID
     * @return 用户信息
     */
    Future<User> getUserById(Long id);
}
