package com.lxy.threadDemo;

class CreateThreadDemo02 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("thread, i = " + i);
        }
    }
}


public class ThreadDemo02 {

    public static void main(String[] args) {

        CreateThreadDemo02 t = new CreateThreadDemo02();
        Thread thread = new Thread(t);
        thread.start();

        for (int i = 0; i < 30; i++) {
            System.out.println("main, i = " + i);
        }
    }
}
