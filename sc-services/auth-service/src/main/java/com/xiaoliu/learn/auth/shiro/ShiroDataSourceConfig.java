package com.xiaoliu.learn.auth.shiro;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: 数据源配置类
 * @author: liufb
 * @create: 2020/6/15 15:59
 **/
@Configuration
@MapperScan(basePackages = "com.xiaoliu.learn.auth.mapper", sqlSessionTemplateRef = "shiroSqlSessionTemplate")
public class ShiroDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid.shiro-datasource")
    @Bean(name = "shiroDataSource")
    public DataSource shiroDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "sessionFactoryShiro")
    public SqlSessionFactory sessionFactoryShiro(@Qualifier(value = "shiroDataSource") DataSource dataSource,
                                                 PaginationInterceptor paginationInterceptor,
                                                 @Qualifier(value = "globalConfigurationShiro") GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        Interceptor[] interceptors = new Interceptor[]{paginationInterceptor};
        bean.setPlugins(interceptors);
        bean.setGlobalConfig(globalConfiguration);
        return bean.getObject();
    }

    @ConfigurationProperties(prefix = "global-config-shiro")
    @Bean(name = "globalConfigurationShiro")
    public GlobalConfiguration globalConfigurationShiro() {
        return new GlobalConfiguration();
    }

    @Bean(name = "transactionManagerShiro")
    public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("shiroDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "shiroSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sessionFactoryShiro") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
