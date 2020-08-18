package com.xiaoliu.xatransaction.service.impl;

import com.xiaoliu.xatransaction.entity.Credit;
import com.xiaoliu.xatransaction.entity.User;
import com.xiaoliu.xatransaction.mapper.test01.UserMapper;
import com.xiaoliu.xatransaction.mapper.test02.CreditMapper;
import com.xiaoliu.xatransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 用户管理Service
 * @author: liufb
 * @create: 2020/8/5 9:25
 **/
@Service
@Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CreditMapper creditMapper;

    @Override
    public void save(User user) {
        // 新增用户记录
        userMapper.insert(user);
        // 初始化积分记录
        initCredit(user);
    }

    /**
     * 初始化用户积分数据
     *
     * @param user 用户对象
     */
    private void initCredit(User user) {
        Credit credit = new Credit();
        credit.setUserAccountId(user.getId());
        credit.setPoint(0.0);
        creditMapper.insert(credit);
    }

}
