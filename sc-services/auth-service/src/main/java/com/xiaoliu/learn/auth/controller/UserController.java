package com.xiaoliu.learn.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户管理
 * @author: FuBiaoLiu
 * @date: 2018/12/12
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value = "测试", notes = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bdate", dataType = "String", value = "开始时间"),
            @ApiImplicitParam(paramType = "query", name = "edate", dataType = "String", value = "结束时间")
    })
    @RequiresPermissions("user_test")
    public String test() {
        return "hello world";
    }
}
