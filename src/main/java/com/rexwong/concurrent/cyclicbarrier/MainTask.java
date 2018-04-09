package com.rexwong.concurrent.cyclicbarrier;

public class MainTask implements Runnable{
    @Override
    public void run() {
        System.out.println("......终于要执行最后的任务了......");
    }
}
