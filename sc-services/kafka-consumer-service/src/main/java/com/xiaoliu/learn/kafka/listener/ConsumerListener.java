package com.xiaoliu.learn.kafka.listener;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoliu.common.tools.JsonUtil;
import com.xiaoliu.common.tools.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @description: 消费者监听
 * @author: FuBiaoLiu
 * @date: 2019/9/4
 */
@Slf4j
@Component
public class ConsumerListener {

    /**
     * 消费dev_gp_tosql主题的消息
     *
     * @param record
     */
    @KafkaListener(topics = {"dev_gp_tosql"})
    public void listenGPMessage(ConsumerRecord<?, String> record) {
        if (record == null || StringUtils.isBlank(record.value())) {
            return;
        }
        JSONObject jsonObject = JSON.parseObject(record.value());
        String sql = JsonUtil.getString(jsonObject, "statement");
        String errorCode = JsonUtil.getString(jsonObject, "code");
        if (StringUtils.isBlank(sql) || "10001".equals(errorCode)) {
            return;
        }
        sql = StringUtils.replaceSpecialStrToBlank(sql);
        log.info("SQL Message:{}", sql);
        writeAllToFile(record.value());
        writeSqlToFile(sql);
    }

    /**
     * 消费xiaoliu-topic主题的消息
     *
     * @param record
     */
    // @KafkaListener(topics = {"xiaoliu-topic"})
    public void listenXiaoliuMessage(ConsumerRecord<?, String> record) {
        if (record == null || StringUtils.isBlank(record.value())) {
            return;
        }
        System.out.println("Message:" + record.value());
    }

    private void writeAllToFile(String content) {
        writeToFile("E:\\GpLog\\gp.log", content + "\n");
    }

    private void writeSqlToFile(String sql) {
        writeToFile("E:\\GpLog\\gp_sql.log", "{" + sql + "}\n");
    }

    private void writeToFile(String file, String content) {
        try (FileWriter out = new FileWriter(file, true)) {
            out.write(content);
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }

}
