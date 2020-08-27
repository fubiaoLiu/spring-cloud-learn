-- 交易流水表
CREATE TABLE `tb_transaction_flow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `from_acct_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '源账户',
  `to_acct_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标账户',
  `amount` double(10, 2) NOT NULL COMMENT '金额',
  `status` tinyint(4) NOT NULL COMMENT '状态（0.交易中;1.交易成功;2.交易失败）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易流水表' ROW_FORMAT = Dynamic;

-- bytetcc框架表
CREATE TABLE bytejta (
  xid  varchar(32),
  gxid varchar(40),
  bxid varchar(40),
  ctime bigint(20),
  PRIMARY KEY (xid)
);