package com.xiaoliu.learn.rocketmq.config;

import org.apache.logging.log4j.util.Strings;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 生产者配置类
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Configuration
public class ProducerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfiguration.class);

    /**
     * Producer组名，多个Producer如果属于一个应用，发送同样的消息，则应该将它们归为同一组。默认DEFAULT_PRODUCER
     */
    @Value("${rocketmq.producer.producerGroup}")
    private String producerGroup;
    /**
     * namesrv地址
     */
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    /**
     * 客户端限制的消息大小，超过报错，同时服务端也会限制，需要跟服务端配合使用。默认4MB
     */
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    /**
     * 发送消息超时时间，单位毫秒。默认10000
     */
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    /**
     * 如果消息发送失败，最大重试次数，该参数只对同步发送模式起作用。默认2
     */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;
    /**
     * 消息Body超过多大开始压缩（Consumer收到消息会自动解压缩），单位字节。默认4096
     */
    @Value("${rocketmq.producer.compressMsgBodyOverHowmuch}")
    private Integer compressMsgBodyOverHowmuch;
    /**
     * 在发送消息时，自动创建服务器不存在的topic，需要指定Key，该Key可用于配置发送消息所在topic的默认路由。
     */
    @Value("${rocketmq.producer.createTopicKey}")
    private String createTopicKey;

    @Bean
    public DefaultMQProducer getRocketMQProducer() {

        DefaultMQProducer producer = new DefaultMQProducer(this.producerGroup);
        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setCreateTopicKey(this.createTopicKey);

        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        if (this.compressMsgBodyOverHowmuch != null) {
            producer.setCompressMsgBodyOverHowmuch(this.compressMsgBodyOverHowmuch);
        }
        if (Strings.isNotBlank(this.createTopicKey)) {
            producer.setCreateTopicKey(this.createTopicKey);
        }

        try {
            producer.start();

            LOGGER.info("Producer Started : producerGroup:[{}], namesrvAddr:[{}]"
                    , this.producerGroup, this.namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error("Producer Start Failed : {}", e.getMessage(), e);
        }
        return producer;
    }

}
