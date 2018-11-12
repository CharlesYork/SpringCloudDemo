package com.sino.custom.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.sino.pojo.User;
import org.springframework.web.client.RestTemplate;


/**
 * 通过继承HystrixCommand的方式实现 通过Hystrix监管下执行restTemplate的调用，一般使用注解的方式。
 * @author CharlesYork
 */
public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter, RestTemplate restTemplate , Long id){
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}",User.class,id);
    }

}
