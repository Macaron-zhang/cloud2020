package com.atguigu.springcloud.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced       //开启RestTemplate远程微服务的负载均衡（Ribbon）：默认轮询
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
