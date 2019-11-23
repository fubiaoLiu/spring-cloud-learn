package config;

import com.netflix.loadbalancer.IRule;
import com.xiaoliu.learn.config.ConsumerLoadBalancerRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 调用Provider服务的负载均衡策略配置类，配置后需在启动类中指定
 * @author: FuBiaoLiu
 * @date: 2019/11/23
 */
@Configuration
public class ProviderFeignRuleConfig {
    @Bean
    public IRule iRule() {
        return new ConsumerLoadBalancerRule();
    }
}
