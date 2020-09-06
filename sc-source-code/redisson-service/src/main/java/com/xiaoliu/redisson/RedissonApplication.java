package com.xiaoliu.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @description: Redisson Demo
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
public class RedissonApplication {
    public static void main(String[] args) throws Exception {
        // singleServer();
        // clusterServerReentrantLock();
        clusterServerFairLock();
    }

    private static RedissonClient getRedissonClient() {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://192.168.2.141:7001")
                .addNodeAddress("redis://192.168.2.141:7002")
                .addNodeAddress("redis://192.168.2.141:7003")
                .addNodeAddress("redis://192.168.2.134:7001")
                .addNodeAddress("redis://192.168.2.134:7002")
                .addNodeAddress("redis://192.168.2.134:7003");

        // or read config from file
        // config = Config.fromYAML(new File("config-file.yaml"));

        // 2. Create Redisson instance
        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    /**
     * 基于redis cluster集群的公平锁
     *
     * @throws Exception
     */
    private static void clusterServerFairLock() throws Exception {
        RedissonClient redisson = getRedissonClient();
        RLock fairLock = redisson.getFairLock("anyLock");

        fairLock.lock();
        Thread.sleep(3600000);
        fairLock.unlock();
    }

    /**
     * 基于redis cluster集群的可重入锁
     *
     * @throws Exception
     */
    private static void clusterServerReentrantLock() throws Exception {
        RedissonClient redisson = getRedissonClient();

        // Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("myLock");

        lock.lock();
        Thread.sleep(3600000);
        lock.unlock();
    }

    /**
     * 基于redis单实例简单看RedLock源码
     */
    private static void singleServer() {
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

