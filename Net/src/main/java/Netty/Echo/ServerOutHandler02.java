package com.fcy.Net.Netty.Echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  22:27
 */
public class ServerOutHandler02 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("ServerOutHandler02...write"+msg.toString());
        super.write(ctx,msg,promise);
    }
}
