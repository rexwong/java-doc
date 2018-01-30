package com.rexwong.concurrent.lock.atomic;

import com.rexwong.concurrent.lock.jvmlock.OrderService;
import com.rexwong.concurrent.lock.task.OrderTask;
import com.rexwong.concurrent.lock.utils.OrderAtomicService;
import com.rexwong.concurrent.lock.utils.OrderLockService;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JvmAtomicOrder {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch latch=new CountDownLatch(1);
        OrderService orderService = new OrderAtomicService();
        for (int i = 0; i <10 ; i++) {
            executorService.submit(new OrderTask(latch,orderService));
        }
        latch.countDown();
        executorService.shutdown();
    }
}
