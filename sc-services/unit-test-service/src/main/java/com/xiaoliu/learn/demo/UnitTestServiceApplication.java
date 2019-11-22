package com.xiaoliu.learn.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 启动类
 * @author: FuBiaoLiu
 * @date: 2019/6/28
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.xiaoliu.learn.demo.mapper")
public class UnitTestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitTestServiceApplication.class, args);
    }
}

