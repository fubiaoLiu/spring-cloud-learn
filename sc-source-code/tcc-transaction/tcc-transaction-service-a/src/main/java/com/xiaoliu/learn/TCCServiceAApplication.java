package com.xiaoliu.learn;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudSecondaryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @description: TCC服务A启动类
 * 按请求粒度负载均衡:需引入SpringCloudConfiguration; <br />
 * 按事务粒度负载均衡:需引入SpringCloudSecondaryConfiguration;
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
@Import(SpringCloudSecondaryConfiguration.class)
@EnableFeignClients
@SpringBootApplication
public class TCCServiceAApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCServiceAApplication.class, args);
        System.out.println("tcc-transaction-service-a started!");
    }
}

