package com.xiaoliu.learn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @description: 网关启动类
 * @author: FubiaoLiu
 * @date: 2018/11/28
 */
@EnableZuulProxy
@SpringBootApplication
@EnableFeignClients
@ComponentScans({
        @ComponentScan("com.xiaoliu.common.utils")
})
@EnableRetry
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * zuul动态路由
     *
     * @return
     */
    /*@Bean(name = "zuul.CONFIGURATION_PROPERTIES")
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }*/

}
