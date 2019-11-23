package com.xiaoliu.learn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
