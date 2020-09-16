package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//可用于多种注册中心:Nacos,Eureka,Consul,Zookeeper
@EnableDiscoveryClient      //类似于@EnableEurekaClient,是一个通用的服务发现客户端组件,
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class,args);
    }
}
