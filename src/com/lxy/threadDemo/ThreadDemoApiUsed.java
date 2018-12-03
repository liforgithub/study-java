package com.lxy.threadDemo;

//Thread api使用

class CreateThreadDemo04 extends Thread {
    @Override
    public void run() {
        for (int i=0; i < 30; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 5) {
                stop();   //不建议使用
            }

            System.out.println("线程id = " + getId() + ", name = " + getName() + ", i = " + i);
        }
    }
}

public class ThreadDemoApiUsed {

    public static void main(String[] args) {
        System.out.println("主线程id: " + Thread.currentThread().getId() + ", name = " + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            CreateThreadDemo04 t = new CreateThreadDemo04();
            t.start();
        }

    }
}
