package com.xiaoliu.learn.controller;

import com.alibaba.fastjson.JSON;
import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.service.UserServiceApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @description: 用户管理Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RestController
public class UserController implements UserServiceApi {

    @Override
    public User getById(Long id) {
        System.out.println("根据ID查询用户信息，ID = " + id);
        return createUser(id);
    }

    @Override
    public void save(User user) {
        System.out.println("保存用户:" + JSON.toJSON(user));
    }

    @Override
    public void update(User user) {
        System.out.println("更新用户:" + JSON.toJSON(user));
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("删除用户:" + id);
    }

    @Override
    public List<User> getByIds(List<Long> ids) {
        System.out.println("根据ID列表查询用户信息，ID[] = " + ids);
        List<User> users = new ArrayList<>(ids.size());
        for (Long id : ids) {
            users.add(createUser(id));
        }
        return users;
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("用户" + id);
        user.setSex((int) (id % 2));
        user.setPassword("pwd" + new Random().nextInt(100000));
        user.setCreateTime(new Date());
        return user;
    }
}
