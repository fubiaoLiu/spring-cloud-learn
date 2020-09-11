## 调试源码用的微服务
- spring-cloud-assembly: Spring Cloud 组件相关

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

- tcc-transaction: TCC分布式事务相关

  - tcc-transaction-service-a-api: TCC分布式事务提供者服务A的API模块

  - tcc-transaction-service-a: TCC分布式事务提供者服务A | 6005

  - tcc-transaction-service-b: TCC分布式事务消费者服务B | 6006

  - tcc-transaction-service-c-api: TCC分布式事务提供者服务C的API模块

  - tcc-transaction-service-c: TCC分布式事务提供者服务C`为了更好的演示confirm、cancel时程序的执行情况，增加服务C` | 6007

  - tcc-transaction-service-d-api: TCC分布式事务提供者服务D的API模块

  - tcc-transaction-service-d: TCC分布式事务提供者服务D`为了更好的演示confirm、cancel时程序的执行情况，增加服务D` | 6008
    ```text
    通过 bytetcc框架 实现TCC分布式事务
    
    服务调用链：
    服务B     -> 服务A      -> 服务D
    服务B     -> 服务C
    服务B     -> 服务D
    ```
    服务调用链：
    
    |  一级服务  |  二级服务  |  三级服务  |
    |  :----:  |  :----:  |  :----:  |
    |  服务B  |  服务A<br>服务C<br>服务D  |  服务D<br><br><br>  |
    
    <table>
        <caption>服务调用链</caption>
        <tr>
            <th>一级服务</th>
            <th>二级服务</th>
            <th>三级服务</th>  
        </tr >
        <tr >
            <td rowspan="3">服务B</td>
            <td>服务A</td>
            <td>服务D</td>
        </tr>
        <tr>
            <td>服务C</td>
            <td></td>
        </tr>
        <tr>
            <td>服务D</td>
            <td></td>
        </tr>
    </table>

- reliable-message: 可靠消息最终一致性方案

  - reliable-message-service-api: 可靠消息服务的API模块

  - reliable-message-service: 可靠消息服务 | 6009

- best-effort-inform: 最大努力通知方案