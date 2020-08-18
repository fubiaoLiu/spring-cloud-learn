package com.xiaoliu.xatransaction.controller;


import com.xiaoliu.xatransaction.entity.User;
import com.xiaoliu.xatransaction.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户管理Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void save(User user) {
        userService.save(user);
    }
}
