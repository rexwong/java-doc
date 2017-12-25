package com.rexwong.concurrent.lock.utils;

import com.rexwong.concurrent.lock.jvmlock.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderLockService extends AbstractOrderService implements OrderService{
    static int num=0;
    //对象锁：orderService1 orderService2
    synchronized public String getOrderId() {
        SimpleDateFormat data = new SimpleDateFormat("YYYYMMDDHHMMSS");
        return data.format(new Date())+num++;
    }
}
