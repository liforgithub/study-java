package com.lxy.threadLockDemo;

import java.util.concurrent.CyclicBarrier;

/*
* jdk1.5并发包
* CyclicBarrier 初始化时传入线程数，当线程await到初始化的线程数的时候，在放开等待，继续执行
* */
public class ThreadDemoCyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + ",正在写入数据");
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.println("线程" + Thread.currentThread().getName() + ",写入数据成功.....");

                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("所有线程执行完毕..........");

            }).start();
        }
    }
}
