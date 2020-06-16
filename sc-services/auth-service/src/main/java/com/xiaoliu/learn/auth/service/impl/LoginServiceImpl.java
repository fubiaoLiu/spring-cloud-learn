package com.xiaoliu.learn.auth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.xiaoliu.learn.auth.domain.Permission;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.mapper.UserMapper;
import com.xiaoliu.learn.auth.service.LoginService;
import com.xiaoliu.learn.auth.service.PermissionService;
import com.xiaoliu.learn.auth.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description: 登录Service
 * @author: liufb
 * @create: 2020/5/18 16:12
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public User getUserByName(String username) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("username", username);
        System.out.println(ew.getSqlSegment());
        User user = SqlHelper.getObject(this.userMapper.selectList(ew));
        if (user == null) {
            return null;
        }
        List<Role> roles = roleService.selectRoleByUser(user);
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                List<Permission> permissions = permissionService.selectPermByRole(role);
                role.setPermissions(new HashSet<>(permissions));
            }
        }
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}
