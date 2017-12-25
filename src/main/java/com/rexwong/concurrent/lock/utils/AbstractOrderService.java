package com.rexwong.concurrent.lock.utils;

import com.rexwong.concurrent.lock.jvmlock.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractOrderService implements OrderService{
    static int num=0;
    //static：类锁，一个OrderService.class
    public static synchronized String getOrder2(){
        SimpleDateFormat data = new SimpleDateFormat("YYYYMMDDHHMMSS");
        return data.format(new Date())+num++;

    }
    public String getOrderId(){
        return getOrder2();
    }
}
