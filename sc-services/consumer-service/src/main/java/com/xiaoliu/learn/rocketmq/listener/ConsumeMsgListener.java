package com.xiaoliu.learn.rocketmq.listener;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 消费者监听器
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Component
public class ConsumeMsgListener implements MessageListenerConcurrently {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeMsgListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            LOGGER.info("Msgs is Empty.");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt msg : msgs) {
            try {
                if ("user-topic".equals(msg.getTopic())) {
                    LOGGER.info("{} Receive New Messages: {}", Thread.currentThread().getName(), new String(msg.getBody()));
                    // do something
                }
            } catch (Exception e) {
                if (msg.getReconsumeTimes() == 3) {
                    // 超过3次不再重试
                    LOGGER.error("Msg Consume Failed.");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } else {
                    // 重试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
