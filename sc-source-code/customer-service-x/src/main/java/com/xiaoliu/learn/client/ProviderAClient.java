package com.xiaoliu.learn.client;

import com.xiaoliu.common.service.UserServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: 提供者服务A客户端
 * @author: liufb
 * @create: 2020/7/31 12:17
 **/
@FeignClient(name = "provider-service-a")
public interface ProviderAClient extends UserServiceApi {
}
