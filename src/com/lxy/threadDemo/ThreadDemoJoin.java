package com.lxy.threadDemo;

public class ThreadDemoJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("子线程， i = " + i);
            }
        });
        t.start();
        t.join();  //子线程先执行完毕，在主线程运行
        for (int i = 0; i < 30; i++) {
            System.out.println("主线程， i = " + i);
        }
    }
}
