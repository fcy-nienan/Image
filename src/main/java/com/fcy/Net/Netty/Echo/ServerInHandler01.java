package com.fcy.Net.Netty.Echo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  17:33
 */
public class ServerInHandler01 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerInHandler01:");
        ctx.fireChannelRead(msg);
    }
}
