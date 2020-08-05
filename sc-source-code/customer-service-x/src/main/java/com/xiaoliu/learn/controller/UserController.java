package com.xiaoliu.learn.controller;

import com.xiaoliu.common.entity.User;
import com.xiaoliu.learn.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description: 用户管理Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RestController
@RequestMapping("/customer/user")
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

    @GetMapping("/cache/{id}")
    public User requestCache(@PathVariable("id") Long id) {
        User user1 = userService.getById(id);
        User user2 = userService.getById(id);
        System.out.println("User1 : " + user1.toString());
        System.out.println("User2 : " + user2.toString());

        userService.deleteById(id);

        User user3 = userService.getById(id);
        User user4 = userService.getById(id);
        System.out.println("User3 : " + user3.toString());
        System.out.println("User4 : " + user4.toString());

        return user1;
    }

    @GetMapping("/merge/{id}")
    public User getUserById(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        Future<User> userFuture = userService.getUserById(id);
        return userFuture.get();
    }

    /*@GetMapping("/merge/")
    public List<User> getUsersByIds() {
        int size = 10;
        List<User> users = new ArrayList<>(size);
        for (int id = 1; id <= size; id++) {
            new Thread(){

            }.start();
            users.add(userService.getUserById((long) id));
        }
        return users;
    }*/
}
