package Reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerDemo {
    private  int flag = 0;
    private  int BLOCK = 4096;
    private  ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
    private  ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;

    public void test(int port)throws Exception{
        ServerSocketChannel open = ServerSocketChannel.open();
        open.configureBlocking(false);
        open.bind(new InetSocketAddress(port));
        Selector selector=Selector.open();
        Selector open1 = Selector.open();
        open.register(open1,SelectionKey.OP_READ);
        SelectionKey register = open.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isReadable()){

                }else if (next.isWritable()){

                }else if (next.isAcceptable()){

                }
            }
        }
    }
    public NIOServerDemo(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
//        ServerSocket serverSocket = serverSocketChannel.socket();
//        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    // 监听
    private void listen() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleKey(selectionKey);
            }
        }
    }
    // 处理请求
    private void handleKey(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count=0;
        if (selectionKey.isAcceptable()) {
            server = (ServerSocketChannel) selectionKey.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {  //可读事件
            client = (SocketChannel) selectionKey.channel();
            receiveBuffer.clear();
            count = client.read(receiveBuffer);
            if (count > 0) {
                receiveText = new String( receiveBuffer.array(),0,count);
                System.out.println("服务器端接受客户端数据--:"+receiveText);
                client.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {  //可写事件(一般基本每次遍历都有可写的事件)
            sendBuffer.clear();
            client = (SocketChannel) selectionKey.channel();
            sendText="message from server:" + flag++;
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();
            client.write(sendBuffer);
            System.out.println("服务器端向客户端发送数据--："+sendText);
            client.register(selector, SelectionKey.OP_READ);
        }
    }
    public static void main(String[] args) throws IOException {
        int port = 10099;
        NIOServerDemo server = new NIOServerDemo(port);
        server.listen();
    }
}
