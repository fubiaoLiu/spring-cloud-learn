package com.xiaoliu.learn.bus.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
