package com.rexwong.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyCurrentDemo {
    public static void main(String[] args) {
//        baseDemo();
    }

    private static void fixedPoolDemo() {
        Executor executor= Executors.newFixedThreadPool(1);//等同于newSingleThreadExecutor()
        executor.execute(new Runnable(){
            public void run() {
                System.out.print("thread1");
            }
        });
    }
    private static void schedulePoolDemo() {
        Executor executor= Executors.newScheduledThreadPool(1);
        executor.execute(new Runnable(){
            public void run() {
                System.out.print("thread1");
            }
        });
    }
}
