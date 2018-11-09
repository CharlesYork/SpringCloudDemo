package com.didispace.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charles York
 * @Description 服务的注册方
 * @Date  2018/11/9
 * @Param
 * @return
**/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index(){
        return "hello world";
    }
}

