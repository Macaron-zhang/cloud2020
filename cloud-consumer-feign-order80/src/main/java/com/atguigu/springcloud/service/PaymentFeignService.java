package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component      //声明在IOC容器中,被创建对象
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /*远程服务接口:没有实现类,是通过代理实现接口的
    * 这个接口方法要与远程微服务的controller的方法声明一致
    * */
    //OpenFeign支持SpringMVC的注解,可以解析@RequestMapping注解下的接口并通过动态代理的方法产生实现类,实现类做负载均衡并调用其他服务
    //当我们80的controller调用这个service时,service接口就会根据负载均衡策略,去相应的服务器去调用与接口方法一致的800x的controller方法,实现基于接口的远程调用
    @RequestMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @RequestMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
