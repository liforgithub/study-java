package com.lxy.threadDemo;


/*
* 守护线程
* 和主线程一起结束
* */
public class ThreadDemoDeamon {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            for (int i = 0; i < 3000; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程: i = " + i);
            }
        });
        t.setDaemon(true);  //设置为守护线程
        t.start();
        Thread.sleep(200);
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程: i = " + i);
        }
        System.out.println("主线程执行完毕");
    }
}
