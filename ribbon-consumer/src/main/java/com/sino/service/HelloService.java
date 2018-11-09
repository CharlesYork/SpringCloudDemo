package com.sino.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
/**
 * @Author Charles York
 * @Description
 * @Date  2018/11/9
 * @Param
 * @return
**/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 同步
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        return restTemplate.getForEntity("http://eureka-producer/hello",String.class).getBody();
    }

    /**
     * 在Hystrix的注解命令下执行的restTemplate ,可以实现同步和异步的调用（异步）
     * @return
     */
    @HystrixCommand
    public Future<String> helloServiceAsync(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://eureka-producer/hello",String.class).getBody();
            }
        };
    }

    /**
     * @Author Charles York
     * @Description 服务降级的返回显示页面
     * @Date  2018/11/9
     * @Param
     * @return
    **/
    public String helloFallback(){
        return "error";
    }

}
