package com.xiaoliu.learn.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.mapper.RoleMapper;
import com.xiaoliu.learn.auth.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色Service
 * @author: liufb
 * @create: 2020/6/15 10:37
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> selectRoleByUser(User user) {
        return baseMapper.selectRoleByUser(user);
    }
}
