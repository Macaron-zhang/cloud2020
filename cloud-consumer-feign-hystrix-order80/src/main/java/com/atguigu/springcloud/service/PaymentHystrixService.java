package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//使用注解@FeignClient 定义feign客户端 ;
@Component      //将它的实现类对象纳入IOC容器进行管理,在这里没有实现类,是通过动态代理实现接口
//CLOUD-HYSTRIX-PAYMENT-SERVICE代表要远程调用的服务器群,fallback = PaymentFallbackService.class代表兜底方法实现类
@FeignClient(name = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_Timeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/ArtifactException/{id}")
    public String payment_ArtifactException(@PathVariable("id") Integer id);
}
