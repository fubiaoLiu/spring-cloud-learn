package com.xiaoliu.transaction.controller;


import com.xiaoliu.transaction.entity.User;
import com.xiaoliu.transaction.service.UserService;
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

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/")
    public void save(User user) {
        userService.save(user);
    }

    @PutMapping("/")
    public void update(User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/transaction/")
    public void transaction() throws Exception {
        userService.transaction();
    }

    @PostMapping("/method/a")
    public void methodA() throws Exception {
        userService.methodA();
    }
}
