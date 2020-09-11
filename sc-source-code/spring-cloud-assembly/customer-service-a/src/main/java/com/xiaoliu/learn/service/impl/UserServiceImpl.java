package com.xiaoliu.learn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.client.ProviderAClient;
import com.xiaoliu.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @description: 用户管理Service
 * @author: liufb
 * @create: 2020/8/5 9:25
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ProviderAClient providerAClient;

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(commandKey = "user_id")
    @Override
    public User getById(Long id) {
        return providerAClient.getById(id);
    }

    @Override
    public void save(User user) {
        providerAClient.save(user);
    }

    @Override
    public void update(User user) {
        providerAClient.update(user);
    }

    @CacheRemove(commandKey = "user_id", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    @Override
    public void deleteById(Long id) {
        providerAClient.deleteById(id);
    }

    /**
     * 第一种方法没有使用@CacheKey注解，而是使用这个方法进行生成cacheKey的替换办法
     * 这里有两点要特别注意：
     * 1、这个方法的入参的类型必须与缓存方法的入参类型相同，如果不同被调用会报这个方法找不到的异常
     * 2、这个方法的返回值一定是String类型
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    /**
     * 请求合并时被合并的方法
     *
     * @param id ID
     * @return 用户信息Future（返回的需要Future或Observable）
     */
    @HystrixCollapser(batchMethod = "getUsersByIds", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "3000")})
    @Override
    public Future<User> getUserById(Long id) {
        System.out.println("-----------UserServiceImpl#getUserById(Long)-----------");
        return null;
    }

    /**
     * 请求合并时真正执行的方法
     *
     * @param ids 入参需要是List，不能是数组
     * @return 用户列表
     */
    @HystrixCommand
    public List<User> getUsersByIds(List<Long> ids) {
        System.out.println("-----------UserServiceImpl#getUsersByIds(Array)-----------");
        return providerAClient.getByIds(ids);
    }
}
