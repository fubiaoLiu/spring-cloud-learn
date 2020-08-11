package com.xiaoliu.learn.client;

import com.xiaoliu.common.entity.User;
import com.xiaoliu.common.service.UserServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 提供者服务A客户端
 * @author: liufb
 * @create: 2020/7/31 12:17
 **/
@FeignClient(name = "provider-service-a", fallbackFactory = ProviderAClient.ProviderAClientFallbackFactory.class)
public interface ProviderAClient extends UserServiceApi {

    @Component
    class ProviderAClientFallbackFactory implements FallbackFactory<ProviderAClient> {
        private static ProviderAClient fallback = new ProviderAClient() {
            @Override
            public User getById(Long id) {
                System.out.println("getById()方法的降级机制");
                return createDefaultUser();
            }

            @Override
            public void save(User user) {
                System.out.println("save()方法的降级机制");
            }

            @Override
            public void update(User user) {
                System.out.println("update()方法的降级机制");
            }

            @Override
            public void deleteById(Long id) {
                System.out.println("deleteById()方法的降级机制");
            }

            @Override
            public List<User> getByIds(List<Long> ids) {
                System.out.println("getByIds()方法的降级机制");
                List<User> users = new ArrayList<>(1);
                users.add(createDefaultUser());
                return users;
            }
        };

        private static User createDefaultUser() {
            User user = new User();
            user.setId(0L);
            user.setUsername("默认用户");
            user.setPassword("123456");
            user.setSex(0);
            user.setCreateTime(new Date());
            return user;
        }

        @Override
        public ProviderAClient create(Throwable cause) {
            // 这里可以根据异常类型选择不同的降级策略
            return fallback;
        }
    }
}
