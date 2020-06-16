package com.xiaoliu.learn.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoliu.learn.auth.domain.Permission;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.mapper.PermissionMapper;
import com.xiaoliu.learn.auth.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 权限Service
 * @author: liufb
 * @create: 2020/6/15 10:44
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> selectPermByRole(Role role) {
        return baseMapper.selectPermByRole(role);
    }

    @Override
    public List<Permission> selectPermByUser(User user) {
        return baseMapper.selectPermByUser(user);
    }
}
