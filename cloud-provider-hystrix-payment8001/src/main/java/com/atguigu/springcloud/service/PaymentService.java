package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @description:
 * @author: Dawn
 * @create: 2020-06-19 11:49
 **/
@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String PaymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfo_OK, id: " +id +"\t ^_^";
    }

    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String PaymentInfo_Timeout(Integer id){
        int timeNumber = 3;
//        int timeNumber = 1;
//        int age = 10/0;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfo_Timeout, id: " +id +"\t ^_^" + "耗时"+timeNumber+"秒钟";
    }
    public String PaymentInfo_TimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " 8001系统繁忙或者运行报错，请稍后再试, id: " +id +"\t o(╥﹏╥)o";
    }
}
