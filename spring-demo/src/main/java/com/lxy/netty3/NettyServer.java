package com.lxy.netty3;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerHandle extends SimpleChannelHandler {

    //接受客户端数据
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("messageReceived");
        System.out.println("服务器端获取客户端的值: " + e.getMessage());
        ctx.getChannel().write("你好.....(from server)");
    }

    //接收端出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    //必须要建立连接，关闭的时候触发
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }

    //通道被关闭的时候触发
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }
}

public class NettyServer {

    public static void main(String[] args) {

        //创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //创建两个线程池, 监听端口号，nio监听

        ExecutorService boosPool = Executors.newCachedThreadPool();
        ExecutorService workPool = Executors.newCachedThreadPool();
        //将线程池放入工程
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boosPool, workPool));
        //设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            //设置管道
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                //传输数据的时候直接为String类型
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                //设置事件监听类
                pipeline.addLast("serverHandle", new ServerHandle());

                return pipeline;
            }
        });

        //绑定端口号
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("netty服务器已经启动.....");
    }
}
