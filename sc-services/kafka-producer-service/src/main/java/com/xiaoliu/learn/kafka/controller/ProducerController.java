package com.xiaoliu.learn.kafka.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 生产者Controller
 * @author: FuBiaoLiu
 * @date: 2019/9/4
 */
@RestController
@RequestMapping("/consumer")
@Api(tags = "RocketMQ消费者管理")
public class ProducerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/message/send")
    public boolean send(@RequestParam String message) {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("xiaoliu-topic", message + i);
        }
        LOGGER.info("xiaoliu-topic:消息发送成功！");
        return true;
    }
}
