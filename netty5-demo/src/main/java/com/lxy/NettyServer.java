package com.lxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

class ServerHandler extends ChannelHandlerAdapter {
    /**
     * 当通道被调用,执行该方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收数据
        String value = (String) msg;
        System.out.println("Server msg:" + value);
        // 回复给客户端 “您好!”
        String res = "好的...";
        ctx.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));
    }

}


public class NettyServer {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("netty服务器已启动.....");

        //创建两个线程池 一个接受客户端消息， 另一个进行数据传输
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        NioEventLoopGroup cGroup = new NioEventLoopGroup();
        // 2. 创建服务器辅助类
        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                // 3.设置缓冲区与发送区大小
                .option(ChannelOption.SO_SNDBUF, 32 * 1024).option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture cf = b.bind(8080).sync();
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
