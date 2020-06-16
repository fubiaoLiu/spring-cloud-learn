package com.xiaoliu.learn.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.mapper.UserMapper;
import com.xiaoliu.learn.auth.service.UserService;
import org.springframework.stereotype.Service;


/**
 * @description: 用户Service
 * @author: liufb
 * @create: 2020/6/15 11:42
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
