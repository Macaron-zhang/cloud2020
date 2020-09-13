package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data       //等价于 @Getter和@Setter
@AllArgsConstructor     //全参构造器
@NoArgsConstructor      //无参构造器
public class CommonResult<T> implements Serializable {
    private Integer code;   //状态码
    private String message;     //携带的消息
    private T data;     //数据泛型

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }


}
