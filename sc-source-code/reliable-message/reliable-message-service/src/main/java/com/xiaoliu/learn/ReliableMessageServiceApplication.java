package com.xiaoliu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 可靠消息服务启动类
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
@EnableFeignClients
@SpringBootApplication
public class ReliableMessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReliableMessageServiceApplication.class, args);
    }
}

