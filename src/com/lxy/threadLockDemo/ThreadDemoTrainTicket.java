package com.lxy.threadLockDemo;

class ThreadTicket implements Runnable {
    private int trainCount = 100;

    @Override
    public void run() {
        while (trainCount > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sale();
        }
    }

    private void sale() {
        System.out.println(Thread.currentThread().getName() + ", 出售第 " + (100 - trainCount + 1) + "张票");
        trainCount--;
    }
}

public class ThreadDemoTrainTicket {

    public static void main(String[] args) {

        ThreadTicket t = new ThreadTicket();
        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口2");
        t1.start();
        t2.start();
    }
}
