package com.lxy.threadDemo;

public class ThreadDemo03 {

    //使用匿名内部类
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for (int i=0; i < 30; i++) {
                System.out.println("thread, i = " + i);
            }
        });
        t.start();
        for (int i=0; i < 30; i++) {
            System.out.println("main, i = " + i);
        }
    }
}
