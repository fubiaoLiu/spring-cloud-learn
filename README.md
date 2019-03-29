# Spring Cloud 学习Demo

## 1. Modules
###  sc-common
公共基础类库
###  sc-modules
系统组件库，包含SpringCloud基础组件:
- Eureka Server: 服务治理
- Config Server： 配置中心
- Zuul: 网关

###  sc-services
#### 业务微服务:

- producer-service: RocketMQ 生产者服务
```$xslt
RocketMQ Producer Demo
配置类：com.xiaoliu.learn.rocketmq.config.ProducerConfiguration
测试类：com.xiaoliu.learn.rocketmq.ProducerServiceApplicationTests

直接使用Demo(同官方的入门演示案例类似)：com.xiaoliu.learn.rocketmq.controller.ProducerController
```
- consumer-service: RocketMQ 消费者服务
```$xslt
RocketMQ Consumer Demo
配置类：com.xiaoliu.learn.rocketmq.config.ConsumerConfiguration
监听器：com.xiaoliu.learn.rocketmq.listener.ConsumeMsgListener

直接使用Demo(同官方的入门演示案例类似)：com.xiaoliu.learn.rocketmq.controller.ConsumerController
```