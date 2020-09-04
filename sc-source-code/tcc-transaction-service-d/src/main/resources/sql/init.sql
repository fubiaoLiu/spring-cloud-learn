-- 消息发送记录表
CREATE TABLE `tb_send_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `status` int(4) NOT NULL COMMENT '发送状态(0.发送中;1.发送成功;2.发送失败)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录表';

-- bytetcc框架表
CREATE TABLE bytejta (
  xid  varchar(32),
  gxid varchar(40),
  bxid varchar(40),
  ctime bigint(20),
  PRIMARY KEY (xid)
);