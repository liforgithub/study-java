package com.lxy.threadLockDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemoAtomicInteger extends Thread {

    //jdk1.5   原子操作
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            atomicInteger.incrementAndGet();
        }

        System.out.println(atomicInteger);
    }

    public static void main(String[] args) {

        for (int i=0; i < 10; i++) {
            ThreadDemoAtomicInteger threadDemoAtomicInteger = new ThreadDemoAtomicInteger();
            threadDemoAtomicInteger.start();
        }

    }
}
