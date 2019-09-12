package com.xiaoliu.learn.bus.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Rabbit 配置类
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
@Configuration
public class RabbitConfig {
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
