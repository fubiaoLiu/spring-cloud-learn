package com.xiaoliu.learn.rocketmq.controller;

import io.swagger.annotations.Api;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 消费者管理Controller
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@RestController
@RequestMapping("/consumer")
@Api(tags = "RocketMQ消费者管理")
public class ConsumerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    // @PostConstruct
    public void consume() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("xiao_liu");
        consumer.setNamesrvAddr("192.168.101.213:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("user-topic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(msgs)) {
                    for (MessageExt msg : msgs) {
                        LOGGER.info("{} 消费者消费消息: {} %n", Thread.currentThread().getName(), new String(msg.getBody()));
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        LOGGER.info("Consumer Started.%n");
    }
}
