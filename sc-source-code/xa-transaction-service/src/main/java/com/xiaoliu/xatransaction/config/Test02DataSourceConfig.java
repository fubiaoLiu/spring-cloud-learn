package com.xiaoliu.xatransaction.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @description: druid数据库连接池配置类
 * @author: liufb
 * @create: 2020/8/18 11:32
 **/
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