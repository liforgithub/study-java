package com.lxy.socket.udp;

import java.io.IOException;
import java.net.*;

class UdpServer {

    public static void main(String[] args) throws IOException {
        System.out.println("udp服务器启动....");
        //默认使用本机ip地址
        DatagramSocket ds = new DatagramSocket(8080);
        //定义数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dspt = new DatagramPacket(bytes, bytes.length);
        //接受客户端请求，将数据封装成数据包，如果客户端不发送请求，该方法将一直阻塞
        ds.receive(dspt);

        System.out.println("来源IP地址： " + dspt.getAddress() + " 端口号: " + dspt.getPort());
        String result = new String(dspt.getData(), 0, dspt.getLength());
        System.out.println(result);
        ds.close();
    }
}

public class UdpClient {

    public static void main(String[] args) throws IOException {
        System.out.println("udp客户端启动....");
        DatagramSocket ds = new DatagramSocket();
        String str = "udp测试";
        byte[] bytes = str.getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8080);
        ds.send(dp);
        ds.close();
    }
}
