-- 消息发送记录表
CREATE TABLE `tb_send_message`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `status` int(4) NOT NULL COMMENT '发送状态(0.发送中;1.发送成功;2.发送失败)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息发送记录表' ROW_FORMAT = Dynamic;

-- bytetcc框架表
CREATE TABLE bytejta (
  xid  varchar(32),
  gxid varchar(40),
  bxid varchar(40),
  ctime bigint(20),
  PRIMARY KEY (xid)
);