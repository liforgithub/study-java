package com.lxy.threadDemo;

// 什么是线程？ 其实就是程序中 执行的一条路径。
// 一个进程中，一定main主线程 GC线程属于守护线程
// 什麼是守护线程，主线程或者进程停止 该线程就会停止。
// 用户线程 用户自定义的线程--------特征：主线程停止，用户线程不会受影响。

class CreateThreadDemo01 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 30; i ++) {
            System.out.println("run, i = " + i);
        }
    }
}


public class ThreadDemo01 {

    /*
    * 创建线程的方式:
    * 使用继承thread类, 重写run方法
    *
    * */

    public static void main(String[] args) {
        CreateThreadDemo01 t = new CreateThreadDemo01();
        t.start();
        for (int i = 0; i < 30; i ++) {
            System.out.println("main, i = " + i);
        }
    }
}
