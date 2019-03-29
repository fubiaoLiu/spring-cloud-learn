package com.xiaoliu.learn.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.xiaoliu.learn.chartservice.mapper")
public class ChartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChartServiceApplication.class, args);
    }
}

