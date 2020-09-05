package com.xiaoliu.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @description: 事务服务启动类
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
//@SpringBootApplication
public class RedissonApplication {
    public static void main(String[] args) {
//        SpringApplication.run(RedissonApplication.class, args);

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissionClient = Redisson.create(config);

        RLock lock1 = redissionClient.getLock("lock1");
//        lock1.lock();
//        lock1.unlock();
        RLock lock2 = redissionClient.getLock("lock2");
        RLock lock3 = redissionClient.getLock("lock3");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 红锁在大部分节点上加锁成功就算成功。
        lock.lock();
        lock.unlock();
    }
}

