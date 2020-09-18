package com.xiaoliu.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.Date;

/**
 * @description: Redisson Demo
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
public class RedissonApplication {
    private static boolean useCluster = false;

    public static void main(String[] args) throws Exception {
        // reentrantLock();
        // fairLock();
        // multiLock();
        // redLock();
        // readWriteLock();
        // semaphore();
        countDownLatch();
    }

    /**
     * 获取redisson客户端
     *
     * @return RedissonClient
     */
    private static RedissonClient getRedissonClient() {
        return useCluster ? getClusterRedissonClient() : getSingleRedissonClient();
    }

    /**
     * 获取集群环境的redisson客户端，这里搭建了一个3主3从的redis cluster集群
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
     * countDownLatch
     *
     * @throws Exception
     */
    private static void countDownLatch() throws Exception {
        int countDownNum = 3;
        final RedissonClient redisson = getRedissonClient();
        final RCountDownLatch latch = redisson.getCountDownLatch("myCountDownLatch");
        latch.trySetCount(countDownNum);
        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]设置了必须有" + countDownNum + "个线程执行countDown，进入等待中。。。");

        for (int i = 0; i < countDownNum; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]在做一些操作，请耐心等待。。。。。。");
                        Thread.sleep(3000);
                        RCountDownLatch localLatch = redisson.getCountDownLatch("myCountDownLatch");
                        localLatch.countDown();
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]执行countDown操作");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, "CountDown-" + i).start();
        }

        latch.await();
        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]收到通知，有" + countDownNum + "个线程都执行了countDown操作，退出等待继续往下走");
    }

    /**
     * 信号量
     *
     * @throws Exception
     */
    private static void semaphore() throws Exception {
        RedissonClient redisson = getRedissonClient();
        final RSemaphore semaphore = redisson.getSemaphore("mySemaphore");
        semaphore.trySetPermits(3);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]尝试获取Semaphore锁");
                        semaphore.acquire();
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]成功获取到了Semaphore锁，开始工作");
                        Thread.sleep(3000);
                        semaphore.release();
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]释放Semaphore锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }

    }

    /**
     * 读写锁
     *
     * @throws Exception
     */
    private static void readWriteLock() throws Exception {
        RedissonClient redisson = getRedissonClient();
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("mylock");

//        RLock readLock = readWriteLock.readLock();
//        readLock.lock();
//        Thread.sleep(60000);
//        readLock.unlock();

        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        writeLock.unlock();
    }

    /**
     * 红锁: 在大部分节点上加锁成功就算成功
     *
     * @throws Exception
     */
    private static void redLock() throws Exception {
        RedissonClient redisson = getRedissonClient();
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
        // 时加锁：lock1 lock2 lock3
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);

        redLock.lock();
        // Thread.sleep(3600000);
        redLock.unlock();
    }

    /**
     * Multi锁
     *
     * @throws Exception
     */
    private static void multiLock() throws Exception {
        RedissonClient redisson = getRedissonClient();
        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
        RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);

        multiLock.lock();
        // Thread.sleep(3600000);
        multiLock.unlock();
    }

    /**
     * 公平锁
     *
     * @throws Exception
     */
    private static void fairLock() throws Exception {
        RedissonClient redisson = getRedissonClient();
        RLock fairLock = redisson.getFairLock("myLock");

        fairLock.lock();
        // fairLock.tryLock(10, TimeUnit.SECONDS);
        Thread.sleep(3600000);
        fairLock.unlock();
    }

    /**
     * 可重入锁
     *
     * @throws Exception
     */
    private static void reentrantLock() throws Exception {
        RedissonClient redisson = getRedissonClient();

        // Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("myLock");

        lock.lock();
        lock.lock();
        // lock.tryLock(10, TimeUnit.SECONDS);
        // Thread.sleep(3600000);
        lock.unlock();
        lock.unlock();
    }
}

