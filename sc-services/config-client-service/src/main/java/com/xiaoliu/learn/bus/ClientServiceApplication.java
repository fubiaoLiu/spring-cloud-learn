package com.xiaoliu.learn.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 配置中心客户端服务启动器
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
@EnableFeignClients
@SpringBootApplication
public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}

