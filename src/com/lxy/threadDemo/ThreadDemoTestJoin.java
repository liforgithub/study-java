package com.lxy.threadDemo;

//t1 ,t2, t3顺序进行
public class ThreadDemoTestJoin {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("t1.i = " + i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                t1.join();   //关键
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 30; i++) {
                System.out.println("t2.i = " + i);
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                t2.join(); //关键
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 30; i++) {
                System.out.println("t3.i = " + i);
            }
        });
        t3.start();
    }
}
