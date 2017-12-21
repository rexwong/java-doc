package com.rexwong.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private void testLock(){
        lock.lock();
        try {

        }
        finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {

    }
}
