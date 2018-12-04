package com.lxy.threadLockDemo;

class Res {
    public String userName;
    public String sex;
    public boolean flag;
}

class InputThread extends Thread {

    private Res res;

    InputThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (res) {
                if (res.flag) {
                    try {
                        res.wait(); //让当前线程从运行状态变成休眠状态，并释放锁的资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0) {
                    res.userName = "余胜军";
                    res.sex = "男";
                } else {
                    res.userName = "小红";
                    res.sex = "女";
                }
                count = (count + 1) % 2;
                res.flag = true;
                res.notify();
            }
        }
    }
}

class OutThrad extends Thread {
    private Res res;

    public OutThrad(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                if (!res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.userName + "," + res.sex);

                res.flag = false;
                res.notify();
            }
        }
    }
}

public class ThreadCorrespondDemo {

    public static void main(String[] args) {
        Res res = new Res();
        InputThread inputThread = new InputThread(res);
        OutThrad outThrad = new OutThrad(res);
        inputThread.start();
        outThrad.start();
    }
}
