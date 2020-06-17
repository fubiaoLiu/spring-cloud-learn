package com.xiaoliu.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description: 启动类
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.xiaoliu.learn.mapper")
public class ShardingJdbcServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcServiceApplication.class, args);
    }
}

