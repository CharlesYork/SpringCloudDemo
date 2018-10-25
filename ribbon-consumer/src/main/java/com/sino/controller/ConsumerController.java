package com.sino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer() {
        return restTemplate.getForEntity("http://eureka-producer/hello", String.class).getBody();
    }
}
