package com.rexwong.concurrent.fixedthreadpool;

import java.util.concurrent.*;

/**
 * @author rexwong
 */
public class MyCurrentDemo {
    public static void main(String[] args) {
//        fixedPoolDemo();
//        schedulePoolDemo();
        myFixedThreadPool();
    }

    private static void fixedPoolDemo() {
        ExecutorService executor= Executors.newFixedThreadPool(1);//等同于newSingleThreadExecutor()
        Future future=executor.submit(new Runnable(){
            public void run() {
                System.out.println("thread1");
            }
        });
        executor.shutdown();


    }
    private static void schedulePoolDemo() {
        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
        executor.schedule(new Runnable(){
            public void run() {
                System.out.println("thread2");
            }
        },2, TimeUnit.SECONDS);
        executor.schedule(new Runnable(){
            public void run() {
                System.out.println("thread5");
            }
        },5, TimeUnit.SECONDS);
        executor.shutdown();
    }
    private static void myFixedThreadPool(){
        TimingThreadPool  exec = new TimingThreadPool(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        exec.submit(new Runnable(){
            public void run(){
                System.out.println("thread1");
            }
        });
    }
}
