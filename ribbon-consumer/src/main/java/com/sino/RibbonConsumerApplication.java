package com.sino;

import com.sino.config.RibbonClientDefaultConfigurationTestsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * @Author Charles York
 * @Description //TODO
 * @Date  2018/11/12
 * @Param
 * @return
**/
@ServletComponentScan
@EnableCircuitBreaker
@RibbonClient(name = "REST-CONSUMER",configuration = RibbonClientDefaultConfigurationTestsConfig.class)
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
