package com.xiaoliu.learn.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description: 消息生产者
 * @author: liufb
 * @create: 2020/9/9 13:29
 **/
@Component
public class MessageProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_RELIABLE_MESSAGE,
                RabbitConfig.ROUTINGKEY_RELIABLE_MESSAGE,
                message,
                correlationId);
    }

}