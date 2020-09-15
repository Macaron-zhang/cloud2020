package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){

        try {
            int i = paymentService.create(payment);
            if(i == 1){
                log.debug("保存成功-payment"+payment);
                return new CommonResult(200,"保存成功",payment);      //返回一下调用者的数据
            }else {
                log.debug("保存失败-payment"+payment);
                return new CommonResult(444,"保存失败");      //不返回调用者的数据

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("系统异常"+e.getMessage());
            return new CommonResult(999,"系统异常");
        }

    }

    @RequestMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        System.out.println("port="+port);       //查看负载均衡策略调用哪个端口的服务器{8001,8002,8003}

        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);
        try {
            if (payment!=null){  //说明有数据，能查询成功
                return new CommonResult(200,"查询成功",payment);
            }else {
                return new CommonResult(444,"没有对应记录，查询ID："+id,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(999,"系统异常");
        }
    }
    //超时设置
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();} //单位秒
        return port;
    }

}
