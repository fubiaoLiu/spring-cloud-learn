package com.xiaoliu.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Curator Demo
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
public class CuratorApplication {
    public static void main(String[] args) throws Exception {
        // testZookeeperNormal();
        // reentrantLock();
        // semaphore();
        // semaphoreMutex();
        // readWriteLock();
        multiLock();
    }

    /**
     * 多锁
     *
     * @throws Exception
     */
    private static void multiLock() throws Exception {
        CuratorFramework client = getClient();
        List<InterProcessLock> locks = new ArrayList<>();
        InterProcessMutex lock01 = new InterProcessMutex(client, "/locks/multi_lock01");
        InterProcessMutex lock02 = new InterProcessMutex(client, "/locks/multi_lock02");
        InterProcessMutex lock03 = new InterProcessMutex(client, "/locks/multi_lock03");
        locks.add(lock01);
        locks.add(lock02);
        locks.add(lock03);

        InterProcessMultiLock lock = new InterProcessMultiLock(locks);
        lock.acquire();
        lock.release();
    }

    /**
     * 可重入读写锁
     *
     * @throws Exception
     */
    private static void readWriteLock() throws Exception {
        CuratorFramework client = getClient();
        String path = "/locks/rw_lock";
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, path);
        lock.readLock().acquire();
        lock.readLock().release();

        lock.writeLock().acquire();
        lock.writeLock().release();
    }

    /**
     * 非可重入锁
     *
     * @throws Exception
     */
    private static void semaphoreMutex() throws Exception {
        CuratorFramework client = getClient();
        String path = "/locks/semaphore_mutex";
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, path);
        lock.acquire();
        Thread.sleep(1000);
        lock.release();
    }

    /**
     * semaphore
     *
     * @throws Exception
     */
    private static void semaphore() throws Exception {
        CuratorFramework client = getClient();
        String path = "/locks/semaphore";
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, path, 3);

        Lease lease = semaphore.acquire();
        Thread.sleep(1000);
        semaphore.returnLease(lease);
    }

    /**
     * 可重入锁
     *
     * @throws Exception
     */
    private static void reentrantLock() throws Exception {
        CuratorFramework client = getClient();
        String path = "/locks/lock_01";

        InterProcessMutex lock = new InterProcessMutex(client, path);
        lock.acquire();
        Thread.sleep(1000);
        lock.release();
    }

    /**
     * 测试zookeeper及curator框架正常
     *
     * @throws Exception
     */
    private static void testZookeeperNormal() throws Exception {
        CuratorFramework client = getClient();

        client.create()
                .creatingParentsIfNeeded()
                .forPath("/my/path", "hello world".getBytes());

        System.out.println(new String(client.getData().forPath("/my/path")));
    }

    /**
     * 获取zookeeper客户端
     *
     * @return zookeeper客户端
     */
    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "192.168.2.127:2181,192.168.2.182:2181,192.168.2.108:2181", retryPolicy);
        client.start();
        return client;
    }
}

