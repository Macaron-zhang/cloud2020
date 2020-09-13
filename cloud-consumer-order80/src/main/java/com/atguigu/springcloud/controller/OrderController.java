package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 订单微服务
 * 消费者：不与数据库打交道，我们只跳转视图
 */
@RestController
public class OrderController {

//    String URL = "http://localhost:8001";
//    从注册中心拿地址---必须先去开启负载均衡---config配置类（@LoadBalanced）
    String URL = "http://CLOUD-PAYMENT-SERVICE";


    @Autowired
     RestTemplate restTemplate;    //注入灵魂

//    @RequestMapping(value = "/customer/payment/getPaymentById/{id}",method = RequestMethod.POST )
    @PostMapping("/customer/payment/getPaymentById/{id}")        //这个路径是被浏览器调用的
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @PostMapping("/customer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(URL + "/payment/create", payment, CommonResult.class);
    }
}
