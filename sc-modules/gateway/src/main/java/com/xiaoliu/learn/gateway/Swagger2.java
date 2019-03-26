package com.xiaoliu.learn.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger2配置类
 * @author: Zeng
 * @date: 2018/4/26
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Cloud Learn")
                .description("Spring Cloud Learn API")
                .version("1.0")
                .build();
    }
}
