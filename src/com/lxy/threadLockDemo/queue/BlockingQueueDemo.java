package com.lxy.threadLockDemo.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Producer extends Thread {

    private BlockingQueue queue;
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动....");
        try {
            while (flag) {
                String data = atomicInteger.incrementAndGet() + "";
                //添加到队列
                boolean offer = queue.offer(data);
                if (offer) {
                    System.out.println("生产者添加队列: " + data + "成功");
                } else {
                    System.out.println("生产者添加队列: " + data + "失败");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        } finally {
            System.out.println("生产者线程结束.....");
        }
    }

    public void stopThread() {
        this.flag = false;
    }
}

class Consumer extends Thread {
    private BlockingQueue queue;
    private volatile boolean flag = true;

    Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程启动.......");
        try {
            while (flag) {
                //获取并发队列里面的数据
                String data = (String) queue.poll(2, TimeUnit.SECONDS);
                if (data != null) {
                    System.out.println("消费者线程获取数据 data=" + data + " 成功");
                } else {
                    System.out.println("消费者线程获取数据 data=" + data + " 失败");
                    this.flag = false;
                }
            }
        } catch (Exception e) {

        } finally {
            System.out.println("消费者线程结束.....");
        }
    }
}

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);

        p1.start();
        p2.start();
        c1.start();

        Thread.sleep(10 * 1000);
        p1.stopThread();
        p2.stopThread();
    }
}
