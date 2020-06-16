package com.xiaoliu.learn.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoliu.learn.auth.domain.Permission;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;

import java.util.List;

/**
 * @description: 权限Service
 * @author: liufb
 * @create: 2020/6/15 10:44
 **/
public interface PermissionService extends IService<Permission> {

    /**
     * 根据角色查询权限列表
     *
     * @param role 角色信息
     * @return 权限列表
     */
    List<Permission> selectPermByRole(Role role);

    /**
     * 根据用户查询权限列表
     *
     * @param user 用户信息
     * @return 权限列表
     */
    List<Permission> selectPermByUser(User user);
}
