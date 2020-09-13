package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Autowired
    PaymentFeignService paymentFeignService;        //符合面向接口编程的特色,//调用远程的微服接口

    @RequestMapping("/payment/getPaymentById/{id}")     //被浏览器访问时的地址
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
            return paymentFeignService.getPaymentById(id);
    }

    @RequestMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);

    }


}
