package com.xiaoliu.learn.bus.endpoint;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 客户端
 * @author: FubiaoLiu
 * @date: 2019/6/2
 */
@Component
@Api(tags = "生产者")
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public Producer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send() {
        String content = "hello : " + System.currentTimeMillis();
        LOGGER.info("Producer : {}", content);
        amqpTemplate.convertAndSend("hello", content);
    }
}
