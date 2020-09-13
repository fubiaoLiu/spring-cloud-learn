package com.xiaoliu.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @description: Curator Demo
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
public class CuratorApplication {
    public static void main(String[] args) throws Exception {
        // testZookeeperNormal();
        reentrantLock();
    }

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

