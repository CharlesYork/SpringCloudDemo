package com.sino.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.sino.pojo.User;
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
     * @Author Charles York
     * @Description //使用@CacheKey方式设置请求缓存,如果使用@CacheKey("id")，
     * 会报java.beans.IntrospectionException: Method not found: isId，目前还不知道什么原因。
     * @Date  2018/11/12
     * @Param
     * @return
    **/
    @CacheResult
    @HystrixCommand
    public User getUserByIdUsingCacheKey(@CacheKey("id") Long id){
        return restTemplate.getForObject("http://USER-SERVICE/user/{1}",User.class,id);
    }


    /**
     * @Author Charles York
     * @Description //TODO 使用cacheKeyMethod请求缓存。
     *  报异常return type of cacheKey method must be String.
     *  当使用String作为返回参数时会出现java.lang.IllegalStateException:
     *  Request caching is not available. Maybe you need to initialize the HystrixRequestContext?
     *  通过定义Filter 中定义上下文解决。详情看{@com.sino.filter.HystrixRequestContextServletFilter}
     *  在启动类上标注@ServletComponentScan
     * @Date  2018/11/12
     * @Param
     * @return
    **/
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand(commandKey = "CacheKeyMethod")
    public User getUserByIdUsingCacheKeyMethod(Long id){
        return restTemplate.getForObject("http://USER-SERVICE/user/{1}",User.class,id);
    }

    /**
     * @Author Charles York
     * @Description //TODO
     * 1、这个方法的入参的类型必须与缓存方法的入参类型相同，如果不同被调用会报这个方法找不到的异常
     * 2、这个方法的返回值一定是String类型
     * @Date  2018/11/12
     * @Param
     * @return
    **/
    private String getUserByIdCacheKey(Long id){
        System.out.println("cacheDo");
        System.out.println(id.toString());
        return id.toString();
    }

    /**
     * @Author Charles York
     * @Description //TODO
     * 必须指定commandKey才能进行清除指定缓存
     * @Date  2018/11/12
     * @Param
     * @return
    **/
    @CacheRemove(commandKey = "CacheKeyMethod",cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand
    public void updateUserByIdUsingCacheRemove(Long id){
        System.out.println("更新"+id.toString());
        //这里不写更新了，理解意思。
    }



    /**
     * 同步调用
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
