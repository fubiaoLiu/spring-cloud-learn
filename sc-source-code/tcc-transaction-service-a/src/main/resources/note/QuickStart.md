# Quick Start
### 一、添加Maven依赖
由于我这里使用MySQL数据库测试，并且注册中心用的Eureka，所以把supports中的其他依赖`（mongodb、zookeeper、consul等等）`排除
```xml
<!-- bytetcc -->
<dependency>
    <groupId>org.bytesoft</groupId>
    <artifactId>bytetcc-supports-springcloud</artifactId>
    <version>0.5.7</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-zookeeper-core</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
        <exclusion>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-zookeeper-discovery</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-consul-discovery</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb-cross-store</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb-log4j</artifactId>
        </exclusion>
        <exclusion>
            <artifactId>mongo-java-driver</artifactId>
            <groupId>org.mongodb</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

### 二、数据库增加依赖表
```sql
CREATE TABLE bytejta (
  xid  varchar(32),
  gxid varchar(40),
  bxid varchar(40),
  ctime bigint(20),
  PRIMARY KEY (xid)
);
```

### 三、启动类引入相关配置类`也可以在@Configuration类上引入`
启动类引入相关配置类，根据负载均衡粒度引入不用的配置类：
- 请求粒度: 引入SpringCloudConfiguration
- 事务粒度: 引入SpringCloudSecondaryConfiguration
```java
@Import(SpringCloudSecondaryConfiguration.class)
@SpringBootApplication
public class TCCServiceAApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCServiceAApplication.class, args);
        System.out.println("tcc-transaction-service-a started!");
    }
}
```

### 四、改造接口（因为接口类中有两个接口，所以只能使用一般模式。如果接口类中只有一个接口可以使用简单模式，简单模式可以查看官方wiki）
#### 1、定义请求API接口的interface类
我这里定义了两个业务接口（转入和转出：两个功能没有关联关系），后续说明以`/account/transferIn`接口为例。

```java
@RequestMapping("/account")
public interface AccountServiceApi {
    /**
     * 转入
     *
     * @param acctId 用户ID
     * @param amount 转入金额
     */
    @PutMapping("/transferIn")
    void transferIn(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 转出
     *
     * @param acctId 用户ID
     * @param amount 转出金额
     */
    @PostMapping("/transferOut")
    void transferOut(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);
}
```

#### 2、定义请求API接口的实际接口类（try接口）
这里执行是try的逻辑，在这个接口锁定资源，比如冻结账户的转账金额。
注：
- 使用`@Compensable`注解指定`interfaceClass`、`confirmableKey`、`cancellableKey`。
  - interfaceClass: 就是`步骤1`中定义的interface接口。
  - confirmableKey: 同样实现`步骤1`中定义的interface接口，对应方法中是`confirm`逻辑。`（步骤3）`
  - cancellableKey: 同样实现`步骤1`中定义的interface接口，对应方法中是`cancel`逻辑。`（步骤4）`
- 相关方法使用`@Transactional`注解标注。

```java
@Compensable(
        interfaceClass = AccountServiceApi.class,
        confirmableKey = "accountServiceConfirm",
        cancellableKey = "accountServiceCancel")
@RestController
public class AccountController implements AccountServiceApi {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void transferIn(String acctId, double amount) {
        accountService.tryTransferIn(acctId, amount);
    }

    @Override
    @Transactional
    public void transferOut(String acctId, double amount) {
        accountService.frozenAmount(acctId, amount);
    }
}
```

#### 3、定义请求API接口的confirm接口（bytetcc调用）
用于完成实际的业务逻辑，比如完成实际的转账操作。
注：
- Spring Bean Name和`步骤2`配置的`confirmableKey`要一致。
- 因`AccountServiceApi`类中指定了http request地址，为了不冲突，这里指定为其他地址，不会通过这个地址调用接口。
- 相关方法使用`@Transactional`注解标注。
- 不能使用`@Compensable`注解标注。

```java
@RequestMapping("/account/confirm")
@Service("accountServiceConfirm")
public class AccountServiceConfirm implements AccountServiceApi {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void transferIn(String acctId, double amount) {
        accountService.confirmTransferIn(acctId, amount);
        // throw new RuntimeException();
    }

    @Override
    @Transactional
    public void transferOut(String acctId, double amount) {
        accountService.transferOut(acctId, amount);
    }
}
```

#### 4、定义请求API接口的cancel接口（bytetcc调用）
用于回滚try阶段变更的数据，比如解冻账户的转账金额。
注：
- Spring Bean Name和`步骤2`配置的`cancellableKey`要一致。
- 因`AccountServiceApi`类中指定了http request地址，为了不冲突，这里指定为其他地址，不会通过这个地址调用接口。
- 相关方法使用`@Transactional`注解标注。
- 不能使用`@Compensable`注解标注。

```java
@RequestMapping("/account/confirm")
@Service("accountServiceConfirm")
public class AccountServiceConfirm implements AccountServiceApi {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void transferIn(String acctId, double amount) {
        accountService.confirmTransferIn(acctId, amount);
        // throw new RuntimeException();
    }

    @Override
    @Transactional
    public void transferOut(String acctId, double amount) {
        accountService.transferOut(acctId, amount);
    }
}
```
`注：3、4步骤功能实现一样，只有实际执行的业务操作有区别`