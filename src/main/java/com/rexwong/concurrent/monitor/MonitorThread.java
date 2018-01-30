package com.rexwong.concurrent.monitor;

import com.rexwong.concurrent.lock.jvmlock.OrderService;
import com.rexwong.concurrent.lock.task.OrderTask;
import com.rexwong.concurrent.lock.utils.OrderAtomicService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 监控线程，用于监控主线程状态，处理异常
 *
 * @author 大朋
 * @since 0.1
 */
public class MonitorThread implements Runnable{

    final static CountDownLatch latch=new CountDownLatch(1);
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        for (int j = 0; j < 100; j++) {
            System.out.printf("i=%s\t\r",j);
        }

    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i <10 ; i++) {
            executorService.submit(new MonitorThread());
        }
        executorService.shutdown();
        WorkThread workThread = new WorkThread(latch);
        workThread.doSomething();
    }
}
