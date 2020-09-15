package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
//解决兜底方法与controller业务逻辑方法混在一起产生的代码混乱问题
public class PaymentFallbackService implements PaymentHystrixService {

    //这里的方法是对应着接口里远程调用失败后的兜底方法
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务失败,请重试";        //
    }

    @Override
    public String payment_Timeout(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }

    @Override
    public String payment_ArtifactException(Integer id) {
        return "服务失败,请重试";
    }
}
