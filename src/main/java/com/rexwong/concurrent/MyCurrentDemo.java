package com.rexwong.concurrent;

import java.util.concurrent.*;

public class MyCurrentDemo {
    public static void main(String[] args) {
//        fixedPoolDemo();
        schedulePoolDemo();
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
}
