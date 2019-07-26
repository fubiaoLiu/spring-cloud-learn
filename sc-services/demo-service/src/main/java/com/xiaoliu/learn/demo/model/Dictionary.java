package com.xiaoliu.learn.demo.model;

import lombok.Data;

/**
 * @description: 数据字典
 * @author: FubiaoLiu
 * @date: 2019/1/8
 */
@Data
public class Dictionary {
    // 主键ID
    private Long id;
    // 字典分类
    private String category;
    // 分类名称
    private String categoryName;
    // 字典值
    private String codeValue;
    // 描述
    private String codeText;
    // 状态(0：启用；1：停用)
    private Integer status;
    // 排序号
    private Integer orderNum;
}
