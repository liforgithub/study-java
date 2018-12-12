package com.lxy.threadLockDemo;

import java.util.Random;
import java.util.concurrent.Semaphore;

/*
 * jsk1.5并发包
 * Semaphore信号量
 * */
public class ThreadDemoSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            String name = "第" + i + "个人";
            new Thread(() -> {
                //减1
                int availableCount = semaphore.availablePermits();
                if (availableCount > 0) {
                    System.out.println(name + "天助我也,终于有茅坑了...");
                } else {
                    System.out.println(name + "怎么没有茅坑了...");
                }

                try {
                    //申请资源
                    semaphore.acquire();
                    System.out.println(name + "终于轮我上厕所了..爽啊");
                    Thread.sleep(new Random().nextInt(1000)); // 模拟上厕所时间。
                    System.out.println(name + "厕所上完了...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //
                    semaphore.release();
                }


            }).start();
        }
    }
}
