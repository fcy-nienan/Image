package Netty.Echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  22:13
 */
public class ServerInHandler02 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerInHandler02...");
        System.out.println("ClientSay:"+msg.toString());
        ctx.write(msg);
    }
}
