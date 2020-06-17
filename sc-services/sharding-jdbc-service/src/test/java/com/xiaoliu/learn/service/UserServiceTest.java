package com.xiaoliu.learn.service;


import com.xiaoliu.learn.ShardingJdbcServiceApplication;
import com.xiaoliu.learn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcServiceApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user;
        for (int i = 1; i <= 10; i++) {
            user = new User("user" + i, "user" + i, i);
            userService.save(user);
        }
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void getByPrimaryKey() {
//        for (int i = 1; i <= 10; i++) {
//            userService.getByPrimaryKey(i);
//        }
    }

    @Test
    public void getUserList() {
    }
}
