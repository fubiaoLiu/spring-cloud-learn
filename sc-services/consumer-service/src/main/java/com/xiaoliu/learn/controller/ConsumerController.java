package com.xiaoliu.learn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xiaoliu.learn.client.ProviderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: consumer
 * @author: FuBiaoLiu
 * @date: 2018/12/12
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private static final String PROVIDER_URL = "http://PROVIDER-SERVICE";
    private RestTemplate restTemplate;
    private ProviderClient providerClient;

    public ConsumerController(RestTemplate restTemplate, ProviderClient providerClient) {
        this.restTemplate = restTemplate;
        this.providerClient = providerClient;
    }

    /**
     * 可以在@HystrixCommand中配置threadPoolProperties属性来设置处理请求的线程池属性，默认所有请求共享同一个线程池，核心线程数为10。
     * 可以在方法上配置threadPoolKey指定单独的线程池，多个方法也可以配置groupKey指定同一个线程池。
     * 超出线程池处理能力范围的请求直接调用降级方法。
     *
     * @return
     */
    @GetMapping("/rest")
    @HystrixCommand(fallbackMethod = "fallBackMethod", threadPoolKey = "provider",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "5")}
    )
//    @HystrixCommand(fallbackMethod = "fallBackMethod")
    public String rest() {
        System.out.println("调用方法：rest");
        /*try {
            // 控制请求时间，模拟多个请求同时在处理，测试限流效果
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        String result = restTemplate.getForObject(PROVIDER_URL + "/provider", String.class);
        System.out.println("rest:" + result);
        return result;
    }

    @GetMapping("/feign")
    @HystrixCommand(fallbackMethod = "fallBackMethod")
    public String feign() {
        System.out.println("调用方法：feign");
        String result = providerClient.provider();
        System.out.println("rest:" + result);
        return result;
    }

    /**
     * 降级方法
     *
     * @return
     */
    private String fallBackMethod() {
        return "ConsumerController: 系统繁忙，请稍后重试...";
    }
}
