package com.xiaoliu.learn.auth.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;

import java.util.List;

/**
 * @description: 角色Mapper
 * @author: liufb
 * @create: 2020/6/15 10:38
 **/
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户查询角色
     *
     * @param user 用户信息
     * @return 用户的角色列表
     */
    List<Role> selectRoleByUser(User user);
}
