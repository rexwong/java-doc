package com.rexwong.concurrent.lock.utils;

import com.rexwong.concurrent.lock.jvmlock.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderAtomicService implements OrderService{
    static AtomicInteger num=new AtomicInteger();
    public String getOrderId() {
        SimpleDateFormat data = new SimpleDateFormat("YYYYMMDDHHMMSS");
        return data.format(new Date())+num.incrementAndGet();
    }
}
