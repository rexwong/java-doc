package com.rexwong.concurrent.lock.jvmlock;

import com.rexwong.concurrent.lock.task.OrderTask;
import com.rexwong.concurrent.lock.utils.OrderLockService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JvmLockOrder {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch latch=new CountDownLatch(1);
        OrderService orderService = new OrderLockService();
        for (int i = 0; i <10 ; i++) {
            executorService.submit(new OrderTask(latch,orderService));
        }
        latch.countDown();
        executorService.shutdown();
    }
}
