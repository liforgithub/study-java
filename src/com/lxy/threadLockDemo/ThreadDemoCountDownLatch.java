package com.lxy.threadLockDemo;

import java.util.concurrent.CountDownLatch;


/*
  jdk1.5并发包的功能
* 保证子线程执行完之后再执行主线程的东西
* CountDownLatch 计数器，初始化是设置次数，当不断调用countDown函数后，直到减少为0时，await()响应，不再等待，继续执行下面的程序
* */
public class ThreadDemoCountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("子线程," + Thread.currentThread().getName() + "开始执行...");
            countDownLatch.countDown();
            System.out.println("子线程," + Thread.currentThread().getName() + "结束执行...");
        }).start();

        new Thread(() -> {
            System.out.println("子线程," + Thread.currentThread().getName() + "开始执行...");
            countDownLatch.countDown();
            System.out.println("子线程," + Thread.currentThread().getName() + "结束执行...");
        }).start();

        countDownLatch.await();
        System.out.println("主线程,开始执行...");
    }
}
