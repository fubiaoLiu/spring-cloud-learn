package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户管理
 * @author: liufb
 * @create: 2020/4/20 14:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public String add(User user) {
        userService.save(user);
        return "success";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.getByPrimaryKey(id);
    }
}
