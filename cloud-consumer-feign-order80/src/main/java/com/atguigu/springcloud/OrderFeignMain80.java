package com.atguigu.springcloud;


import com.atguigu.rule.MyFeignRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableEurekaClient     //开启Eureka注册中心组件
@EnableFeignClients     //OpenFeign基于接口的远程访问组件
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyFeignRule.class)     //自定义负载均衡策略
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
