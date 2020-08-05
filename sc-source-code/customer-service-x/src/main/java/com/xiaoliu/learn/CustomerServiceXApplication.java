package com.xiaoliu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 消费者服务X启动类
 * `@ServletComponentScan`: 扫描自定义Filter
 * `@EnableFeignClients`: 开启Feign客户端扫描
 * `@SpringCloudApplication`: 包含@SpringBootApplication和@EnableCircuitBreaker，
 * `@EnableCircuitBreaker`: 开启dashboard监控
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
@ServletComponentScan
@EnableFeignClients
@SpringCloudApplication
public class CustomerServiceXApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceXApplication.class, args);
    }
}

