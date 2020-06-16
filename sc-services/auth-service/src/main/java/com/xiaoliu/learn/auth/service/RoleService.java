package com.xiaoliu.learn.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;

import java.util.List;

/**
* @description: 角色Service
* @author: liufb
* @create: 2020/6/15 10:43
**/
public interface RoleService extends IService<Role> {

    /**
     * 根据用户查询角色
     *
     * @param user 用户信息
     * @return 用户的角色列表
     */
    List<Role> selectRoleByUser(User user);
}
