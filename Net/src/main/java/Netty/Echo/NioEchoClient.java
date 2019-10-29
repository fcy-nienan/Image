package Netty.Echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  21:01
 */
public class NioEchoClient {
    private static String host="127.0.0.1";
    private static int port=8889;
    public static void main(String[] args) {
        start();
    }
    public static void start(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new ClientHandler01());
                        }
                    });
            ChannelFuture future = b.connect(host, port).sync();
            int x=10;
            while(true) {
                Thread.sleep(3000);
                future.channel().writeAndFlush("Hello Netty Server, I am a common client");
                if (x>20)
                    break;
                x++;
            }
            System.out.println("before close");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.spliterator();
        }
    }

}
