package com.lxy.nio;


import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/*
 * 缓冲区是NIO 提供给传输文件和通道配合使用，存储数据
 * Buffer
 * ByteBuffer
 * LongBuffer
 * IntBuffer
 * shortBuffer
 * FloatBuffer
 * DubooBuffer
 * */
public class demo01 {

    /*
     * 核心属性
     * private int mark = -1;
     * private int position = 0; //缓冲区正在操作的位置,默认从0开始
     * private int limit;        //缓冲区可用大小(全部容量,并非剩余大小)
     * private int capacity;     //缓冲区最大的容量,一旦声明，不能改变
     *
     * 核心方法：
     * put() 存数据
     * get() 取数据
     */
    @Test
    public void BufferTest() {
        //初始化缓冲区为1024
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("--------------------------------------------");

        System.out.println("------开始存放数据--------");
        byteBuffer.put("abcd1".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("------结束存放数据--------");
        System.out.println("--------------------------------------------");

        System.out.println("------开始读取数据--------");
        //开启读取模式 (将position位置定位到开始，默认0)
        byteBuffer.flip();   //读取结束后，position还会回到最后的位置
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());     //读取模式下，limit值代表的是可读取内容的长度
        System.out.println(byteBuffer.capacity());
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("读出的数据: " + new String(bytes, 0, bytes.length));
        System.out.println("------结束读取数据--------");
        System.out.println("--------------------------------------------");

        System.out.println("------重复读取数据--------");
        byteBuffer.rewind();  //重复读取(从上一次开始的地方读取)
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes2 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes2);
        System.out.println("读出的数据: " + new String(bytes2, 0, bytes2.length));
        System.out.println("------重复读取数据--------");
        System.out.println("--------------------------------------------");

        //数值被遗忘，但是还依然存在，依然可以取出来，但是limit被重置为最大值, position返回0
        System.out.println("------清空缓冲区--------");
        byteBuffer.clear();  //重复读取(从上一次开始的地方读取)
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println((char)byteBuffer.get());
        System.out.println("------清空缓冲区--------");
    }

    @Test
    public void BufferTest2() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        String str = "abcd";
        byteBuffer.put(str.getBytes());

//        byteBuffer.flip();
//        byte[] bytes = new byte[byteBuffer.limit()];
//        byteBuffer.get(bytes);
//        System.out.println(new String(bytes, 0, bytes.length));
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes, 0, 2);
        byteBuffer.mark();  //打印标记
        System.out.println(new String(bytes, 0, 2));

        byteBuffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));

        System.out.println(byteBuffer.position());
        System.out.println("-------------reset---------------");
        byteBuffer.reset();  //重置到mark处
        System.out.println(byteBuffer.position());
    }
}
