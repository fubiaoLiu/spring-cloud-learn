package com.xiaoliu.learn.client;

import com.xiaoliu.learn.service.SendMessageServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @description: 服务D客户端
 * @author: liufb
 * @create: 2020/7/31 12:17
 **/
@FeignClient(name = "tcc-transaction-service-d", fallbackFactory = ServiceDClient.ProviderDClientFallbackFactory.class)
public interface ServiceDClient extends SendMessageServiceApi {

    @Component
    class ProviderDClientFallbackFactory implements FallbackFactory<ServiceDClient> {
        private static ServiceDClient fallback = (phone) -> System.out.println("send()方法的降级机制");

        @Override
        public ServiceDClient create(Throwable cause) {
            // 这里可以根据异常类型选择不同的降级策略
            return fallback;
        }
    }
}
