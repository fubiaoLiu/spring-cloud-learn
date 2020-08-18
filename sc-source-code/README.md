## 调试源码用的微服务

- provider-service-a-api: 提供者服务A的API模块

- provider-service-a: 提供者服务A | 6001

- customer-service-a: 消费者服务A | 6002
```$text
使用provider-service-a、customer-service-a和eureka-server、gateway服务调试查看Eureka、Ribbon、Feign、Hystrix、Zuul源码
```

- transaction-service: 事务服务 | 6003

- xa-transaction-service: XA分布式事务服务 | 6004
```text
通过 JTA + Druid多数据源 + Atomikos 三者整合实现分布式事务的管理，底层就是2PC。
```

- tcc-transaction-service: TCC分布式事务服务 | 6005
```text
通过 bytetcc 实现
```

