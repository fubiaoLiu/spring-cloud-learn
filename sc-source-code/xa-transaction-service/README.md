# 基于 JTA + Druid多数据源 + Atomikos 实现XA分布式事务关键步骤

## 一、引入pom依赖
```text
<!-- mysql-connector-java使用8.*版本启动会报错，这里做降版本处理-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.23</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jta-atomikos</artifactId>
</dependency>
```

## 二、代码配置数据源、事务管理器等

### ①、配置文件（这里用的是application.yml，配置内容重点关注下面这段）
```text
spring:
  datasource:
    test-db1:
      type: com.alibaba.druid.pool.xa.DruidXADataSource
      jdbc-url: jdbc:mysql://127.0.0.1:3306/test-db-1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false
      username: root
      password: root
      driverClassName: com.mysql.jdbc.Driver
      initialSize: 50
      minIdle: 50
      maxActive: 500
      maxWait: 60000
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
      maxPoolPreparedStatementPerConnectionSize: 20
      filters: stat,wall
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
    test-db2:
      type: com.alibaba.druid.pool.xa.DruidXADataSource
      jdbc-url: jdbc:mysql://127.0.0.1:3306/test-db-2?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false
      username: root
      password: root
      driverClassName: com.mysql.jdbc.Driver
      initialSize: 50
      minIdle: 50
      maxActive: 500
      maxWait: 60000
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
      maxPoolPreparedStatementPerConnectionSize: 20
      filters: stat,wall
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
```

### ②、配置信息Domain类
**Domain类DataSourceConfig1**
```java
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.test-db1")
public class DataSourceConfig1 {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
}
```

**Domain类DataSourceConfig2**
```java
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.test-db2")
public class DataSourceConfig2 {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
}
```

### ③、配置类初始化数据源（DataSource）、SqlSession工厂（SqlSessionFactory）和事务管理器（TransactionManager）
**配置类Test01DataSourceConfig**
```java
@Configuration
@MapperScan(basePackages = "com.xiaoliu.xatransaction.mapper.test01", sqlSessionFactoryRef = "test01SqlSessionFactory")
public class Test01DataSourceConfig {
    /**
     * 创建druid数据库连接池bean
     *
     * @return
     */
    @Bean(name = "test01DataSource")
    @Primary
    public DataSource test01DataSource(DataSourceConfig1 dataSourceConfig1) {
        DruidXADataSource datasource = new DruidXADataSource();
        datasource.setUrl(dataSourceConfig1.getJdbcUrl());
        datasource.setUsername(dataSourceConfig1.getUsername());
        datasource.setPassword(dataSourceConfig1.getPassword());
        datasource.setDriverClassName(dataSourceConfig1.getDriverClassName());
        datasource.setInitialSize(dataSourceConfig1.getInitialSize());
        datasource.setMinIdle(dataSourceConfig1.getMinIdle());
        datasource.setMaxActive(dataSourceConfig1.getMaxActive());
        datasource.setMaxWait(dataSourceConfig1.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceConfig1.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceConfig1.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceConfig1.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceConfig1.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceConfig1.isTestOnBorrow());
        datasource.setTestOnReturn(dataSourceConfig1.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceConfig1.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig1.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            datasource.setFilters(dataSourceConfig1.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(dataSourceConfig1.getConnectionProperties());

        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSource(datasource);

        return atomikosDataSource;
    }

    @Bean(name = "xatx")
    @Primary
    public JtaTransactionManager test01TransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean(name = "test01SqlSessionFactory")
    @Primary
    public SqlSessionFactory test01SqlSessionFactory(
            @Qualifier("test01DataSource") DataSource test01DataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(test01DataSource);
        return sessionFactory.getObject();
    }
}
```

**配置类Test02DataSourceConfig**
```java
@Configuration
@MapperScan(basePackages = "com.xiaoliu.xatransaction.mapper.test02", sqlSessionFactoryRef = "test02SqlSessionFactory")
public class Test02DataSourceConfig {
    /**
     * 创建druid数据库连接池bean
     *
     * @return
     */
    @Bean(name = "test02DataSource")
    public DataSource test02DataSource(DataSourceConfig2 dataSourceConfig2) {
        DruidXADataSource datasource = new DruidXADataSource();
        datasource.setUrl(dataSourceConfig2.getJdbcUrl());
        datasource.setUsername(dataSourceConfig2.getUsername());
        datasource.setPassword(dataSourceConfig2.getPassword());
        datasource.setDriverClassName(dataSourceConfig2.getDriverClassName());
        datasource.setInitialSize(dataSourceConfig2.getInitialSize());
        datasource.setMinIdle(dataSourceConfig2.getMinIdle());
        datasource.setMaxActive(dataSourceConfig2.getMaxActive());
        datasource.setMaxWait(dataSourceConfig2.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceConfig2.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceConfig2.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceConfig2.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceConfig2.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceConfig2.isTestOnBorrow());
        datasource.setTestOnReturn(dataSourceConfig2.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceConfig2.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig2.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            datasource.setFilters(dataSourceConfig2.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(dataSourceConfig2.getConnectionProperties());

        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSource(datasource);

        return atomikosDataSource;
    }

    @Bean(name = "test02SqlSessionFactory")
    public SqlSessionFactory test02SqlSessionFactory(
            @Qualifier("test02DataSource") DataSource test02DataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(test02DataSource);
        return sessionFactory.getObject();
    }
}
```

## 三、业务代码中使用分布式事务
在需要使用分布式事务的类或方法上使用注解开启事务
```java
@Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
```

## 四、需要注意的点

- 这里用的SpringBoot版本依赖的mysql-connector-java默认是8.*版本的，程序启动会报错，这里做降版本处理。

- application.yml中配置的数据源需要使用com.alibaba.druid.pool.xa.DruidXADataSource。

- 配置类中Druid数据源需要使用DruidXADataSource，最后创建出来的DataSource实际上是被AtomikosDataSourceBean包装后的。