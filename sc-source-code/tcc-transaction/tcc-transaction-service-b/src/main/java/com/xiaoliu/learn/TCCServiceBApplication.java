package com.xiaoliu.learn;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudSecondaryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @description: TCC服务X启动类
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
@Import(SpringCloudSecondaryConfiguration.class)
@EnableFeignClients
@SpringBootApplication
public class TCCServiceBApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCServiceBApplication.class, args);
    }
}

