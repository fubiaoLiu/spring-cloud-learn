package com.xiaoliu.learn.client;

import org.springframework.stereotype.Component;

/**
 * @description: provider feign fall back
 * 指定降级方法的方式：
 * 1、Feign客户端提供默认实现：需要在配置文件中配置开启hystrix(feign.hystrix.enabled=true)，启动类中通过@EnableHystrix注解无法开启。
 * 2、调用的方法上通过@HystrixCommand(fallbackMethod = "fallBackMethod")指定降级方法，
 * 　　需要使用@EnableHystrix注解，配置hystrix(feign.hystrix.enabled=true)无法开启。
 * 1的作用域为该client方法，2的作用域为调用的方法，调用的方法中可能会调用多个client方法。
 * @author: FuBiaoLiu
 * @date: 2019/11/22
 */
@Component
public class ProviderFeignFallBack implements ProviderClient {
    /**
     * '/provider-service/provider' 接口调用
     *
     * @return
     */
    @Override
    public String provider() {
        return "ProviderFeignFallBack: 系统繁忙，请稍后重试...";
    }

    @Override
    public String getById(Long id, String status) {
        return "ProviderFeignFallBack: 系统繁忙，请稍后重试...";
    }
}
