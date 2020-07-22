package com.xiaoliu.learn.controller;

import com.xiaoliu.common.response.CommonResponse;
import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户管理
 * @author: liufb
 * @create: 2020/4/20 14:22
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @PostMapping("/")
    public CommonResponse add(User user) {
        userService.save(user);
        return CommonResponse.success();
    }

    @ApiOperation(value = "根据ID查询用户", httpMethod = "GET")
    @GetMapping("/{id}")
    public CommonResponse get(@PathVariable("id") Long id) {
        return CommonResponse.success(userService.getByPrimaryKey(id));
    }

    @ApiOperation(value = "根据ID删除用户", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public CommonResponse del(@PathVariable("id") Long id) {
        userService.deleteByPrimaryKey(id);
        return CommonResponse.success();
    }
}
