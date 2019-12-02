package com.xiaoliu.learn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @description: 日志实体类
 * @author: FuBiaoLiu
 * @date: 2019/11/28
 */
@Data
@Document(indexName = "log",type = "docs",shards = 1,replicas = 0)
public class Log {
    /** id */
    @Id
    private Long id;
    /** 时间 */
    @Field(type = FieldType.Date)
    private Date date;
    /** 类型(BUSINESS、PLATFORM) */
    @Field(type = FieldType.Keyword)
    private String type;
    /** 级别(ERROR、WARN、INFO、DEBUG) */
    @Field(type = FieldType.Keyword)
    private String level;
    /** 应用名 */
    @Field(type = FieldType.Text)
    private String application;
    /** 应用端口 */
    @Field(type = FieldType.Integer)
    private Integer port;
    /** 日志内容 */
    @Field(type = FieldType.Text)
    private String context;

    public Log(){}

    public Log(Long id, Date date, String type, String level, String application, Integer port, String context) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.level = level;
        this.application = application;
        this.port = port;
        this.context = context;
    }
}
