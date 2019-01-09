package com.fcy.Net.Netty;

import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-09  22:47
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(io.netty.channel.ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
