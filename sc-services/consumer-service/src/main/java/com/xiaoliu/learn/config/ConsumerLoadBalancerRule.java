package com.xiaoliu.learn.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义负载均衡策略（轮循，每个存活的实例连续调用两次）
 * @author: FuBiaoLiu
 * @date: 2019/11/22
 */
@Slf4j
public class ConsumerLoadBalancerRule extends AbstractLoadBalancerRule {
    private AtomicInteger nextServer = new AtomicInteger(0);
    private AtomicInteger callNum = new AtomicInteger(0);
    private static final int MAX_RETRY_COUNT = 10;

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server;

        int count = 0;
        while (count++ < MAX_RETRY_COUNT) {
            log.info("ConsumerLoadBalancerRule");
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            int upCount = upList.size();
            if (upCount == 0 || serverCount == 0) {
                return null;
            }

            server = upList.get(nextServer.get());

            if (server == null) {
                if (nextServer.incrementAndGet() >= serverCount) {
                    nextServer.set(0);
                }
                callNum.set(0);
                Thread.yield();
                continue;
            }

            if (server.isAlive() && server.isReadyToServe()) {
                if (callNum.incrementAndGet() >= 2) {
                    if (nextServer.incrementAndGet() >= serverCount) {
                        nextServer.set(0);
                    }
                    callNum.set(0);
                }
                return server;
            }
        }
        log.warn("No available alive servers after {} tries from load balancer: {}", count, lb);
        return null;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
