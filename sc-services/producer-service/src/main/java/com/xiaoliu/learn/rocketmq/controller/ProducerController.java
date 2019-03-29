package com.xiaoliu.learn.rocketmq.controller;

import com.alibaba.fastjson.JSON;
import com.xiaoliu.common.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: 生产者管理Controller
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@RestController
@RequestMapping("/producer")
@Api(tags = "RocketMQ生产者管理")
public class ProducerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    @PostMapping("/test/swagger")
    @ResponseBody
    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bdate", dataType = "String", value = "开始时间"),
            @ApiImplicitParam(paramType = "query", name = "edate", dataType = "String", value = "结束时间")
    })
    public String testSwagger() {
        return "hello swagger";
    }

    // @PostConstruct
    public void produce() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("xiao_liu");
        producer.setNamesrvAddr("192.168.101.213:9876");
//        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.setUsername("用户" + i);
                user.setPassword("密码" + i);
                user.setSex(i % 2);
                user.setBirthday(new Date());
                Message message = new Message("user-topic", "user-tag", JSON.toJSONString(user).getBytes(RemotingHelper.DEFAULT_CHARSET));
                System.out.printf("%s 生产者生产消息: %s %n", Thread.currentThread().getName(), JSON.toJSONString(user));
                SendResult sendResult = producer.send(message);
                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
