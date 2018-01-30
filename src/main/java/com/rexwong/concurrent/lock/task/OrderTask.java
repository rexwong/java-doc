package com.rexwong.concurrent.lock.task;

import com.rexwong.concurrent.lock.jvmlock.OrderService;

import java.util.concurrent.CountDownLatch;

public class OrderTask implements Runnable{

    private CountDownLatch latch;

    private OrderService orderService;

    public OrderTask(CountDownLatch latch,OrderService orderService) {
        this.latch = latch;
        this.orderService=orderService;
    }
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程名%s订单号：%s \r\n",Thread.currentThread().getName(),orderService.getOrderId());
    }
}
