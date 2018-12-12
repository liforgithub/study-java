package com.lxy.threadLockDemo.queue;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedDequeDemo {

    public static void main(String[] args) {
        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        concurrentLinkedDeque.offer("张三");
        concurrentLinkedDeque.offer("李四");

        //从头获取元素,删除该元素
        System.out.println(concurrentLinkedDeque.poll());
        //从头获取元素,不刪除该元素
        System.out.println(concurrentLinkedDeque.peek());
        //获取总长度
        System.out.println(concurrentLinkedDeque.size());

    }
}
