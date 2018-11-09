package com.sino.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义方式实现ribbon负载均衡的策略配置
 * @author Charles York
 */
@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
public class RibbonClientDefaultConfigurationTestsConfig {

    public static class BazServiceList extends ConfigurationBasedServerList {
        public BazServiceList(IClientConfig config) {
            super.initWithNiwsConfig(config);
        }
    }
}

/**
 * 随机策略实现负载均衡。也可以通过自定义负载均衡策略在此类中 创建和返回以使用
 */
@Configuration
class DefaultRibbonConfig {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }


}