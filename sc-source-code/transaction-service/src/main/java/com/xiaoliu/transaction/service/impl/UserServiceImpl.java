package com.xiaoliu.transaction.service.impl;

import com.xiaoliu.transaction.entity.User;
import com.xiaoliu.transaction.mapper.UserMapper;
import com.xiaoliu.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 用户管理Service
 * @author: liufb
 * @create: 2020/8/5 9:25
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 文档说明 {@link classpath:node.transaction-rollbackFor.md}
     */
    @Override
    @Transactional/*(rollbackFor = Exception.class)*/
    public void transaction() throws Exception {
        User user = new User();
        user.setUsername(String.valueOf(System.currentTimeMillis()));
        user.setPassword("123456");
        method1(user);
//        method2(user);
        try {
            method2(user);
        } catch (Exception e) {
            // throw new Exception();
//            throw new RuntimeException();
        }
        throw new RuntimeException();
//        throw new Exception();
    }

    /**
     * 方法一，新增用户
     *
     * @param user 用户对象
     */
    public void method1(User user) {
        userMapper.insert(user);
    }

    /**
     * 方法二，更新用户
     *
     * @param user 用户对象
     */
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    @Transactional(/*rollbackFor = Exception.class,*/ propagation = Propagation.NESTED)
    public void method2(User user) throws Exception {
//        int i = 1 / 0;

//        throw new Exception();

//        user.setPassword("000000");
//        userMapper.update(user);

        User user2 = new User();
        user2.setUsername(String.valueOf(System.currentTimeMillis()));
        user2.setPassword("000000");
        userMapper.insert(user2);

        throw new Exception();
    }

    /**
     * 文档说明 {@link classpath:node.transaction-propagation.md}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void methodA() throws Exception {
        User user = new User();
        user.setUsername(String.valueOf(System.currentTimeMillis()));
        // ① do something ...
        methodA1(user);
        methodB();
        /*try {
            methodB();
        } catch (Exception e) {
            // throw e;
            // 或
             throw new RuntimeException();
            // 或
            // throw new Error();
            // 或
            // throw new Exception();
        }*/
        // ② do something ...
        methodA2(user);
    }

    private void methodA1(User user) {
        user.setPassword("methodA1");
        userMapper.insert(user);
    }

    private void methodA2(User user) throws Exception {
//        int i = 1 / 0;
        user.setPassword("methodA2");
        userMapper.update(user);
        throw new Exception();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void methodB() throws Exception {
//        int i = 1 / 0;
        User user = new User();
        user.setUsername(String.valueOf(System.currentTimeMillis()));
        user.setPassword("methodB");
        userMapper.insert(user);
//        throw new Exception();
    }
}
