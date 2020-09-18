-- 消息表
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(1024) NOT NULL COMMENT '消息内容',
  `status` int(4) NOT NULL COMMENT '消息状态',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `removed_time` datetime DEFAULT NULL COMMENT '删除时间',
  `confirmed_time` datetime DEFAULT NULL COMMENT '确认时间',
  `finished_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';