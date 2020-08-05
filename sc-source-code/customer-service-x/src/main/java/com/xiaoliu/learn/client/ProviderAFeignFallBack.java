package com.xiaoliu.learn.client;

import com.xiaoliu.common.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: liufb
 * @create: 2020/7/31 13:12
 **/
//@Component
public class ProviderAFeignFallBack implements ProviderAClient {

    @Override
    public User getById(Long id) {
        System.out.println("降级方法");
        User user = new User();
        user.setUsername("默认用户");
        return user;
    }

    @Override
    public void save(User user) {
        System.out.println("降级方法");
    }

    @Override
    public void update(User user) {
        System.out.println("降级方法");
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("降级方法");
    }

    @Override
    public List<User> getByIds(List<Long> ids) {
        System.out.println("降级方法");
        List<User> users = new ArrayList<>(1);
        User user = new User();
        user.setUsername("默认用户");
        users.add(user);
        return users;
    }
}
