package com.xiaoliu.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 程序配置类
 * @author: FuBiaoLiu
 * @date: 2019/11/22
 */
@Configuration
public class ApplicationConfig {
    /**
     * 如果要使用RestTemplate需手动添加到Spring
     * 使用 @LoadBalanced 开启负载均衡，默认使用轮循策略。Ribbon官方提供了7个策略，也可以自定义策略。
     * 开启后可以通过服务名访问其他服务
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 指定为自定义负载均衡策略。
     * 如果需要为多个服务指定不同的策略，则不能配置在ComponentScan的扫描范围内，否则所有的服务会共享该策略。
     * @return
     */
    /*@Bean
    public IRule iRule() {
        return new ConsumerLoadBalancerRule();
    }*/

}
