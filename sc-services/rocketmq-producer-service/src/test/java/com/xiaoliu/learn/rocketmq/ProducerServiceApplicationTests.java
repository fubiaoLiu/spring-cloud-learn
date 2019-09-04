package com.xiaoliu.learn.rocketmq;

import com.alibaba.fastjson.JSON;
import com.xiaoliu.common.entity.User;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerServiceApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceApplicationTests.class);
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void send() throws MQClientException, RemotingException, MQBrokerException, InterruptedException, UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUsername("用户" + i);
            user.setPassword("密码" + i);
            user.setSex(i % 2);
            user.setBirthday(new Date());
            Message message = new Message("user-topic", "user-tag", JSON.toJSONString(user).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 默认3秒超时
            SendResult sendResult = defaultMQProducer.send(message);
            LOGGER.info(sendResult.toString());
        }
    }

}

