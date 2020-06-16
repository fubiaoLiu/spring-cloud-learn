package com.xiaoliu.learn.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 权限服务启动类
 * @author: liufb
 * @create: 2020/6/5 14:26
 **/
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.xiaoliu.learn.auth.mapper")
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}

