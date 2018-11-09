package com.sino.demo2.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @Author Charles York
 * @Description eureka服务本身，可集群。
 * @Date  2018/11/9
 * @Param
 * @return
**/
@EnableEurekaServer
@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}
