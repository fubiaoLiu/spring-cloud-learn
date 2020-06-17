package com.xiaoliu.learn.service.impl;

import com.xiaoliu.common.enums.StatusCodeEnum;
import com.xiaoliu.common.exception.CommonException;
import com.xiaoliu.common.utils.SnowflakesUtil;
import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.entity.UserExample;
import com.xiaoliu.learn.mapper.UserMapper;
import com.xiaoliu.learn.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户管理
 * @author: liufb
 * @create: 2020/4/21 14:50
 **/
@Service
public class UserServiceImpl implements UserService {
    private static final SnowflakesUtil SNOW_FLAKE_TOOLS = new SnowflakesUtil(0L,0L);
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new CommonException(StatusCodeEnum.INVALID_PARAM_ERROR);
        }
        user.setId(SNOW_FLAKE_TOOLS.nextId());
        userMapper.insert(user);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        checkId(id);
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getByPrimaryKey(Long id) {
        checkId(id);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    private void checkId(Long id) {
        if (id == null || id <= 0) {
            throw new CommonException(StatusCodeEnum.INVALID_PARAM_ERROR);
        }
    }
}
