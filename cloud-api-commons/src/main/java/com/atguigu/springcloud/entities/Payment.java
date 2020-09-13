package com.atguigu.springcloud.entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data       //加上它就相当于加上了所有的get/Set方法
@NoArgsConstructor      //无参构造器
@AllArgsConstructor      //全参构造器
@ToString       //toString方法
@EqualsAndHashCode  //equals方法和hashCode方法重写
//@Slf4j  //内建log对象，直接使用打印日志
public class Payment implements Serializable {
    private Integer id;
    private String serial;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(serial, payment.serial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serial);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Payment() {
    }

    public Payment(Integer id, String serial) {
        this.id = id;
        this.serial = serial;
    }*/
}
