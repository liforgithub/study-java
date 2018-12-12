package com.lxy.threadLockDemo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 线程池事例
 * */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        //可缓存的线程池
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            int tmp = i;
//            newCachedThreadPool.execute(() -> System.out.println("name: " + Thread.currentThread().getName() + " i = " + tmp));
//        }
//        newCachedThreadPool.shutdown();

        //可固定长度的线程池
//        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            int tmp = i;
//            newFixedThreadPool.execute(() -> System.out.println("name: " + Thread.currentThread().getName() + " i = " + tmp));
//        }
//        newFixedThreadPool.shutdown();

        //可定时的线程池
//        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
//        for (int i = 0; i < 10; i++) {
//            int tmp = i;
//            newScheduledThreadPool.schedule(() -> System.out.println("name: " + Thread.currentThread().getName() + " i = " + tmp), 3, TimeUnit.SECONDS);
//        }
//        newScheduledThreadPool.shutdown();

        //单线程的线程池
        ExecutorService newSingleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            newSingleThreadPool.execute(() -> System.out.println("name: " + Thread.currentThread().getName() + " i = " + tmp));
        }
        newSingleThreadPool.shutdown();
    }
}
