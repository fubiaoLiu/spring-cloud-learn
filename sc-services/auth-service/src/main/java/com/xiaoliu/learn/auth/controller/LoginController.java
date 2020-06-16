package com.xiaoliu.learn.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户登录管理
 * @author: FuBiaoLiu
 * @date: 2018/12/12
 */
@RestController
@Api(tags = "用户登录管理")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", value = "密码")
    })
    public String login(User user) {
        JSONObject jsonObject = new JSONObject();
        // 添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(), user.getPassword());
        try {
            // 进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @PostMapping("/register")
    public String userRegister(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password) {
        SimpleHash simpleHash = new SimpleHash("MD5", password, ByteSource.Util.bytes(username), 2);
        User user = new User();
        user.setUsername(username);
        user.setPassword(simpleHash.toString());
        userService.insert(user);
        return "success";
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @GetMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

    /**
     * 注解校验角色和权限
     *
     * @return
     */
    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @GetMapping("/index")
    public String index() {
        return "index!";
    }
}
