package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @author: Dawn
 * @create: 2020-06-19 14:52
 */
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.PaymentInfo_OK(id);
        return result;
    }

    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping(value = "/consumer/hystrix/timeout/{id}")
    public String PaymentInfo_Timeout(@PathVariable("id") Integer id){
        int a = 10/0;
        String result = paymentHystrixService.PaymentInfo_Timeout(id);
        return result;
    }
    public String PaymentInfo_TimeoutHandler(Integer id){
        return "我是消费者80，对方支付系统繁忙 请10秒钟后再试或者自己运行出错，请检查自己，o(╥﹏╥)o";
    }
}
