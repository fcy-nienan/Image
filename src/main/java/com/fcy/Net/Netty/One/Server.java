package com.fcy.Net.Netty.One;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  17:03
 */
public class Server {
    public static void main(String[] args) {
        start();
    }
    public static void start(){
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelHandler() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("add");
                    }

                    @Override
                    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("remove");
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        System.out.println(cause.getMessage());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .bind(8080);
    }

}
