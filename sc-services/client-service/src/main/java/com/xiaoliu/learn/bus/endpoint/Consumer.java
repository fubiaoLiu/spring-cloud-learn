package com.xiaoliu.learn.bus.endpoint;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 客户端
 * @author: FubiaoLiu
 * @date: 2019/6/2
 */
@Component
@RabbitListener(queues = "hello")
@Api(tags = "消费者")
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @RabbitHandler
    public void process(String content) {
        LOGGER.info("Consumer : {}", content);
    }
}
