package com.xiaoliu.learn.client;

import com.xiaoliu.learn.service.AccountServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @description: 服务A客户端
 * @author: liufb
 * @create: 2020/7/31 12:17
 **/
@FeignClient(name = "tcc-transaction-service-a", fallbackFactory = ServiceAClient.ProviderAClientFallbackFactory.class)
public interface ServiceAClient extends AccountServiceApi {

    @Component
    class ProviderAClientFallbackFactory implements FallbackFactory<ServiceAClient> {
        private static ServiceAClient fallback = new ServiceAClient() {
            @Override
            public void transferIn(String acctId, double amount) {
                System.out.println("transferIn()方法的降级机制");
            }

            @Override
            public void transferOut(String acctId, double amount) {
                System.out.println("transferOut()方法的降级机制");
            }
        };

        @Override
        public ServiceAClient create(Throwable cause) {
            // 这里可以根据异常类型选择不同的降级策略
            return fallback;
        }
    }
}
