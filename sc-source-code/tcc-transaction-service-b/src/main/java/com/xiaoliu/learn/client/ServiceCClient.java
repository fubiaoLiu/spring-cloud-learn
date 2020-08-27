package com.xiaoliu.learn.client;

import com.xiaoliu.learn.service.TransactionFlowServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @description: 服务C客户端
 * @author: liufb
 * @create: 2020/7/31 12:17
 **/
@FeignClient(name = "tcc-transaction-service-c", fallbackFactory = ServiceCClient.ProviderCClientFallbackFactory.class)
public interface ServiceCClient extends TransactionFlowServiceApi {

    @Component
    class ProviderCClientFallbackFactory implements FallbackFactory<ServiceCClient> {
        private static ServiceCClient fallback =
                (fromAcctId, toAcctId, amount) -> System.out.println("transferIn()方法的降级机制");

        @Override
        public ServiceCClient create(Throwable cause) {
            // 这里可以根据异常类型选择不同的降级策略
            return fallback;
        }
    }
}
