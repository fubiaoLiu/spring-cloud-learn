package com.xiaoliu.learn.rocketmq.config;

import com.xiaoliu.learn.rocketmq.listener.ConsumeMsgListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 消费者配置类
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Configuration
public class ConsumerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfiguration.class);

    @Value("${rocketmq.consumer.consumerGroup}")
    private String consumerGroup;
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Value("${rocketmq.consumer.pullBatchSize}")
    private int pullBatchSize;
    @Value("${rocketmq.consumer.topics}")
    private String topics;

    private final ConsumeMsgListener consumeMsgListener;

    @Autowired
    public ConsumerConfiguration(final ConsumeMsgListener consumeMsgListener) {
        this.consumeMsgListener = consumeMsgListener;
    }

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.setPullBatchSize(pullBatchSize);
        consumer.registerMessageListener(consumeMsgListener);

        try {
            /**
             * 设置消费者订阅的主题和tag。subExpression参数为*表示订阅该主题下所有tag，
             * 如果需要订阅该主题下的指定tag，subExpression设置为对应tag名称，多个tag以||分割，例如"tag1 || tag2 || tag3"
             */
            consumer.subscribe(topics, "*");
            consumer.start();

            LOGGER.info("Consumer Started : consumerGroup:{}, topics:{}, namesrvAddr:{}", consumerGroup, topics, namesrvAddr);
        } catch (Exception e) {
            LOGGER.error("Consumer Start Failed : consumerGroup:{}, topics:{}, namesrvAddr:{}", consumerGroup, topics, namesrvAddr, e);
            e.printStackTrace();
        }

        return consumer;

    }

}
