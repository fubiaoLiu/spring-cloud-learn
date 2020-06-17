package com.xiaoliu.learn.service;

import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.entity.UserExample;

import java.util.List;

/**
 * @description: 用户管理
 * @author: liufb
 * @create: 2020/4/21 14:49
 **/
public interface UserService {
    /**
     * 保存
     *
     * @param user 用户信息
     */
    void save(User user);

    /**
     * 根据主键删除
     *
     * @param id ID
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键查询
     *
     * @param id ID
     * @return 用户信息
     */
    User getByPrimaryKey(Long id);

    /**
     * 获取用户列表
     *
     * @param userExample 条件
     * @return 用户列表
     */
    List<User> getUserList(UserExample userExample);
}
