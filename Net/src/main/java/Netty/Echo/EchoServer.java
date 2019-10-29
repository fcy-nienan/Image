package com.fcy.Net.Netty.Echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  17:31
 */
public class EchoServer {
    private static final int port=8889;
    public static void main(String[] args) throws InterruptedException {
        start();
    }
    public static void start() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("Init Channel....");
                        ch.pipeline().addLast("StringEncode",new StringEncoder());
                        ch.pipeline().addLast("OutHandler01",new ServerOutHandler01());
                        ch.pipeline().addLast("OutHandler02",new ServerOutHandler02());
                        ch.pipeline().addLast("StringDecode",new StringDecoder());
                        ch.pipeline().addLast("InHandler01",new ServerInHandler01());
                        ch.pipeline().addLast("InHandler02",new ServerInHandler02());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        ChannelFuture future=bootstrap.bind(port).sync();
        System.out.println("Server Listen At "+port);
        future.channel().closeFuture().sync();
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
