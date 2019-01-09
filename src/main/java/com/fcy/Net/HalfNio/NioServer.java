package com.fcy.Net.HalfNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-02  10:01
 */
public class NioServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    public static void main(String args[]) throws IOException {
        new NioServer().test();
    }
    public void test() throws IOException {
        selector=Selector.open();
        serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8778));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        while(true){
            int count=selector.select();
            System.out.println(count);
            if(count>0){
                Set<SelectionKey> keys=selector.selectedKeys();
                Iterator iterator=keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key=(SelectionKey)iterator.next();
                    iterator.remove();
                    /**
                    *@descripiton 为什么需要调用remove方法
                     * 看看Register就知道了，注册了的放一个地方，准备好了的放一个地方，select不会删除
                     * 准备好了的，所以需要手动进行remove方法
                    */
                    if(key.isAcceptable()){
                        SocketChannel socketChannel=serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        SocketChannel client= (SocketChannel) key.channel();
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        int num=client.read(byteBuffer);
                        if(num>0){
                            System.out.println(new String(byteBuffer.array(),0,num));
                        }
                    }
                }
            }
//            System.out.println("\r\n\r\n\r\n\r\n");
        }
    }
}
