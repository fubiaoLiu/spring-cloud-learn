package com.xiaoliu.learn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: provider客户端
 * @FeignClient name指定服务名，fallback指定降级方法
 * @author: FuBiaoLiu
 * @date: 2019/11/22
 */
@FeignClient(name = "provider-service", fallback = ProviderFeignFallBack.class)
public interface ProviderClient {
    /**
     * '/provider-service/provider' 接口调用
     *
     * @return
     */
    @RequestMapping("/provider")
    String provider();
    /**
     * '/provider-service/provider' 接口调用
     *
     * @return
     */
    @RequestMapping("/{id}")
    String getById(@PathVariable("id") Long id, @RequestParam("status") String status);
}
