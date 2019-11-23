package com.xiaoliu.learn;

import config.ProviderFeignRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 启动类
 * 注解@RibbonClients可以为多个服务配置不同的负载均衡策略
 * @author: FuBiaoLiu
 * @date: 2019/11/22
 */
@EnableFeignClients
@SpringBootApplication
@RibbonClients({
        @RibbonClient(name = "provider-service", configuration = ProviderFeignRuleConfig.class)
        // ...
})
@EnableHystrix
public class ConsumerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }
}

