package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/6/5 12:21
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)//都小写或都大写
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
