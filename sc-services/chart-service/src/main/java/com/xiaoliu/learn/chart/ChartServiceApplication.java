package com.xiaoliu.learn.chart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.xiaoliu.learn.chart.mapper")
public class ChartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChartServiceApplication.class, args);
    }
}

