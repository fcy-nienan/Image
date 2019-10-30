package Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-09  22:40
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        start();
    }
    public static void start() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap()
                .group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast("decode",new HttpRequestDecoder())
                                .addLast("encode",new HttpResponseEncoder())
                                .addLast("aggregator",new HttpObjectAggregator(512*1025))
                                .addLast(new HttpHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        ChannelFuture future=serverBootstrap.bind(11111).sync();
        future.channel().closeFuture().sync();
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
