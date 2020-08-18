package com.xiaoliu.xatransaction.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.SQLException;

/**
 * @description: druid数据库连接池配置类
 * @author: liufb
 * @create: 2020/8/18 11:09
 **/
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