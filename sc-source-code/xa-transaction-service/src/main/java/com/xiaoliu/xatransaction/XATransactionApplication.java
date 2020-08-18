package com.xiaoliu.xatransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @description: 事务服务启动类
 * @author: liufb
 * @create: 2020/7/31 11:10
 **/
@ServletComponentScan
@SpringBootApplication
public class XATransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(XATransactionApplication.class, args);
    }
}

