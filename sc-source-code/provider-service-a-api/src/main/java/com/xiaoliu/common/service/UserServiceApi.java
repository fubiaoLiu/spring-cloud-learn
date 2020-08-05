package com.xiaoliu.common.service;

import com.xiaoliu.common.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户服务API
 * @author: liufb
 * @create: 2020/7/31 11:24
 **/
@RequestMapping("/user")
public interface UserServiceApi {
    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    @PostMapping("/")
    void save(@RequestBody User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    @PutMapping("/")
    void update(@RequestBody User user);

    /**
     * 根据ID删除用户信息
     *
     * @param id 用户ID
     */
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Long id);

    /**
     * 根据IDs获取用户信息
     *
     * @param ids 用户IDs
     * @return 用户信息列表
     */
    @GetMapping("/")
    List<User> getByIds(@RequestParam List<Long> ids);
}
