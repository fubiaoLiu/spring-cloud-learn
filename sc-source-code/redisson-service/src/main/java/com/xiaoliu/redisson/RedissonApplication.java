package com.xiaoliu.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
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
        // clusterServerFairLock();
        // clusterServerMultiLock();
        // clusterServerRedLock();
        clusterServerReadWriteLock();
    }

    /**
     * 获取集群环境的redisson客户端
     *
     * @return RedissonClient
     */
    private static RedissonClient getClusterRedissonClient() {
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
     * 获取本地单机环境的redisson客户端
     *
     * @return RedissonClient
     */
    private static RedissonClient getSingleRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }

    /**
     * 基于redis cluster集群的读写锁
     *
     * @throws Exception
     */
    private static void clusterServerReadWriteLock() throws Exception {
        RedissonClient redisson = getClusterRedissonClient();
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("mylock");

        RLock readLock = readWriteLock.readLock();
        readLock.lock();
        readLock.unlock();

//        RLock writeLock = readWriteLock.writeLock();
//        writeLock.lock();
//        writeLock.unlock();
    }

    /**
     * 基于redis cluster集群的红锁
     *
     * @throws Exception
     */
    private static void clusterServerRedLock() throws Exception {
        RedissonClient redisson = getClusterRedissonClient();
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);

        redLock.lock();
        // Thread.sleep(3600000);
        redLock.unlock();
    }

    /**
     * 基于redis cluster集群的Multi锁
     *
     * @throws Exception
     */
    private static void clusterServerMultiLock() throws Exception {
        RedissonClient redisson = getClusterRedissonClient();
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
        RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);

        multiLock.lock();
        // Thread.sleep(3600000);
        multiLock.unlock();
    }

    /**
     * 基于redis cluster集群的公平锁
     *
     * @throws Exception
     */
    private static void clusterServerFairLock() throws Exception {
        RedissonClient redisson = getClusterRedissonClient();
        RLock fairLock = redisson.getFairLock("myLock");

        fairLock.lock();
        // fairLock.tryLock(10, TimeUnit.SECONDS);
        Thread.sleep(3600000);
        fairLock.unlock();
    }

    /**
     * 基于redis cluster集群的可重入锁
     *
     * @throws Exception
     */
    private static void clusterServerReentrantLock() throws Exception {
        RedissonClient redisson = getClusterRedissonClient();

        // Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("myLock");

        lock.lock();
        lock.lock();
        // lock.tryLock(10, TimeUnit.SECONDS);
        // Thread.sleep(3600000);
        lock.unlock();
        lock.unlock();
    }

    /**
     * 基于redis单实例简单看RedLock源码
     */
    private static void singleServerRedLock() {
        RedissonClient redissionClient = getSingleRedissonClient();

        RLock lock1 = redissionClient.getLock("lock1");
        RLock lock2 = redissionClient.getLock("lock2");
        RLock lock3 = redissionClient.getLock("lock3");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 红锁在大部分节点上加锁成功就算成功。
        lock.lock();
        lock.unlock();
    }
}

