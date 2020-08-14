# Spring Cloud 学习项目

## 1. Modules
###  sc-common
公共基础类库
###  sc-modules
系统组件库，包含SpringCloud基础组件:
- Eureka Server: 服务治理
- Config Server: 配置中心
- Zuul: 网关
- hystrix-dashboard: 监控

###  sc-services
#### 业务微服务:
- auth-service: 权限微服务(整合Shiro)

- rocketmq-producer-service: RocketMQ 生产者服务
```$xslt
RocketMQ Producer Demo
配置类：com.xiaoliu.learn.rocketmq.config.ProducerConfiguration
测试类：com.xiaoliu.learn.rocketmq.ProducerServiceApplicationTests

直接使用Demo(同官方的入门演示案例类似)：com.xiaoliu.learn.rocketmq.controller.ProducerController
```
- rocketmq-consumer-service: RocketMQ 消费者服务
```$xslt
RocketMQ Consumer Demo
配置类：com.xiaoliu.learn.rocketmq.config.ConsumerConfiguration
监听器：com.xiaoliu.learn.rocketmq.listener.ConsumeMsgListener

直接使用Demo(同官方的入门演示案例类似)：com.xiaoliu.learn.rocketmq.controller.ConsumerController
```

- unit-test-service: 单元测试Demo服务

- sentinel-service: 哨兵服务

- config-client-service: 配置中心client服务

- kafka-consumer-service: Kafka消费者服务

- kafka-consumer-service: Kafka消费者服务

- provider-service1: 服务提供方1

- provider-service2: 服务提供方2

- consumer-service: 服务消费方
```$xslt
使用provider-service和consumer-service模拟服务间的调用、负载均衡、降级、超时、熔断、限流以及链路追踪
```

- elasticsearch-service: elasticsearch服务

- sharding-jdbc-service: 集成sharding-jdbc服务

###  sc-source-code
#### 调试源码用的微服务Demo:
- provider-service-a-api: 提供者服务A的API模块

- provider-service-a: 提供者服务A

- customer-service-a: 消费者服务A
```$xslt
使用provider-service-a、customer-service-a和eureka-server、gateway服务调试查看Eureka、Ribbon、Feign、Hystrix、Zuul源码
```

- transaction-service: 事务服务

###  sc-e-shop
网上商城项目模块