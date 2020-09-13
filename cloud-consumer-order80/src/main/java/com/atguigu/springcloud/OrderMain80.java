package com.atguigu.springcloud;


import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@EnableEurekaClient     //表明为Eureka-client端
//修改了Ribbon组件的默认负载均衡策略，通过configuration属性来指定我们的自定义配置类,
// 指定策略为MySelfRule.class的随机策略 ,使用策略的服务为CLOUD-PAYMENT-SERVICE
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
