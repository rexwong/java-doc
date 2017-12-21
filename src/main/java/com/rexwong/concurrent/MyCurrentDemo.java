package com.rexwong.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyCurrentDemo {
    public static void main(String[] args) {
        Executor executor= Executors.newFixedThreadPool(1);
        executor.execute(new Runnable(){
            public void run() {
                System.out.print("thread1");
            }
        });
    }
}
