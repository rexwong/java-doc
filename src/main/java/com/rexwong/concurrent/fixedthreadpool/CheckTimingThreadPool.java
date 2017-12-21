package com.rexwong.concurrent.fixedthreadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CheckTimingThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor exec = new TimingThreadPool(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        exec.execute(new DoSomething(5));
        exec.execute(new DoSomething(4));
        exec.execute(new DoSomething(3));
        exec.execute(new DoSomething(2));
        exec.execute(new DoSomething(1));
        exec.shutdown();
    }

    static class DoSomething implements Runnable {
        private int sleepTime;

        public DoSomething(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
