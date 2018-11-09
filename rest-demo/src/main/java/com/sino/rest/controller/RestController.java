package com.sino.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Charles York
 * @Description rest-Consumer 服务的消费方
 * @Date  2018/11/9
 * @Param
 * @return
**/
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/msg")
    public String getMsg(){
        return restTemplate.getForObject("http://EUREKA-PRODUCER/hello",String.class);
    }

    @GetMapping("/tmsg")
    public String testMsg(){
        return "tmsg";
    }
}
