package com.rexwong.concurrent.monitor;

import java.util.concurrent.CountDownLatch;

/**
 * 工作线程
 *
 * @author 大朋
 * @since 0.1
 */
public class WorkThread {
//    private Lock ticketLock = new ReentrantLock();
//    Condition conditionObj=ticketLock.newCondition();

    private CountDownLatch latch;

    public WorkThread(CountDownLatch latch){
        this.latch=latch;
    }
    public void doSomething(){
        int i=0;
        while (true){
            System.out.println("");
            i++;
            if(i==100){
                latch.countDown();
            }
        }
    }

}
