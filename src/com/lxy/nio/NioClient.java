package com.lxy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;


class NioServer {
    public static void main(String[] args) throws IOException {
        System.out.println("nio服务端启动");
        //创建socket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置成异步读取
        serverSocketChannel.configureBlocking(false);
        //绑定链接
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器中，并且监听已经接收到的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮训获取“已经准备就绪的”事件
        while (selector.select() > 0) {
            //获取当前选择器有注册已经监听到的事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //获取“准备就绪”事件
                SelectionKey sk = it.next();
                //判断事件“准备就绪”
                if (sk.isAcceptable()) {
                    //若“接收就绪”,获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置阻塞模式
                    socketChannel.configureBlocking(false);  //异步非阻塞io
                    //将该通道注册到服务器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //获取当前选择器“就绪状态”通道
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    //读取数据
                    int len;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                it.remove();
            }
        }
    }
}


//nio客户端
public class NioClient {

    public static void main(String[] args) throws IOException {
        System.out.println("nio客户端启动");

        //创建socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        //切换异步非阻塞
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); //指定缓冲区大小   ---->非直接缓冲区
        byteBuffer.put(new Date().toString().getBytes());
        byteBuffer.flip();  //切换到读取模式
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.close();
    }
}
