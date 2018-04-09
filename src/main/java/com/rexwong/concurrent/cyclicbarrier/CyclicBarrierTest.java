package com.rexwong.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author rexwong
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //创建CyclicBarrier对象，
        //并设置执行完一组5个线程的并发任务后，再执行MainTask任务
        CyclicBarrier cb = new CyclicBarrier(5, new MainTask());
        new SubTask("A", cb).start();
        new SubTask("B", cb).start();
        new SubTask("C", cb).start();
        new SubTask("D", cb).start();
        new SubTask("E", cb).start();
    }

}
