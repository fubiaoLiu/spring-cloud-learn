package com.xiaoliu.learn.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger配置类
 * @author: FubiaoLiu
 * @date: 2019/3/28
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
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaoliu.learn.kafka.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kafka生产者服务")
                .description("Kafka生产者服务API文档")
                .version("1.0")
                .build();
    }
}
