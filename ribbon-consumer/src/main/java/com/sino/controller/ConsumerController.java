package com.sino.controller;

import com.sino.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Charles York
 */
@RestController
public class ConsumerController {


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    HelloService helloService;

    /**
     * 同步调用
     * @return
     */
    @GetMapping("/ribbon-consumer")
    public String helloConsumer(){

        return helloService.helloService();
    }

    /**
     * 异步调用
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/ribbon-consumer/aysnc")
    public String helloConsumerAsync() throws ExecutionException, InterruptedException {
        Future<String> future = helloService.helloServiceAsync();

        System.out.println(future.get());

        return helloService.helloService();
    }

    @GetMapping("/getmsg")
    public String getMsg(){
        return restTemplate.getForObject("http://REST-CONSUMER/tmsg",String.class);
    }

    /**
     * 测试ribbon 负载均衡策略的使用情况。
     * @return
     */
    @GetMapping("/test")
    public String test(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("EUREKA-PRODUCER");
        System.out.println("111"+serviceInstance.getHost()+":"+serviceInstance.getPort()+":"+serviceInstance.getServiceId());

        return "1";
    }
}
