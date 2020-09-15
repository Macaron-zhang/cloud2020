package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderHystrixController {
    //注入使用接口方法
    @Autowired
    PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")        //浏览器访问地址
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }


    //超时降级演示
    //熔断演示
//    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //超过1.5秒就降级自己
//    })
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            //在规定时间(10秒)内出现N次请求,就打开断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "3"),   //当在配置时间窗口内达到此数量的失败后，打开断路，默认20个,这里设置为3个

            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //断路多久以后开始尝试是否恢复，默认5s,这里设置为10s,超过时间断路器就会恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //出错百分比阈值，当达到此阈值后，开始短路。默认50%,这里设置为60%
    })   //百分比阈值和规定时间内出现次数这两个条件是或的关系,满足其中一个就会打开断路器

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        String result = paymentHystrixService.payment_Timeout(id);
//        log.info("*******result:"+result);
//        return result;
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }


    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ArtifactException/{id}")
    public String payment_ArtifactException(@PathVariable("id") Integer id){
        String result = paymentHystrixService.payment_ArtifactException(id);
        log.info("*******result:"+result);
        return result;
    }

    //paymentInfo_TimeOut的兜底方法
    public String payment_TimeoutHandler(Integer id) {
//        return "我是消费者80,对方支付系统繁忙请10秒后再试。或自己运行出错，请检查自己。";
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
