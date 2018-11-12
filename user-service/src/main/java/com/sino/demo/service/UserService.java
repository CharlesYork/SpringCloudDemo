package com.sino.demo.service;

import com.sino.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Charles York
 * @Description //获取User信息的类
 **/
@RestController
public class UserService {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id){
        if(id == 100L) {
            return new User(100L,"Bob",18);
        }
        return null;
    }
}
