package Nio;

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
  
public class NIOServer {
    private  int flag = 0;
    private  int BLOCK = 4096;
    private  ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
    private  ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;  
  
    public NIOServer(int port) throws IOException {  
        // 打开服务器套接字通道  Opens a server-socket channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();  
        // 服务器配置为非阻塞  Adjusts this channel's blocking mode
        serverSocketChannel.configureBlocking(false);  
        // 检索与此通道关联的服务器套接字  Retrieves a server socket associated with this channel
        ServerSocket serverSocket = serverSocketChannel.socket();  
        // 进行服务的绑定  Binds the {@code ServerSocket} to a specific address
        serverSocket.bind(new InetSocketAddress(port));  
        // 通过open()方法找到Selector  Opens a selector.
        selector = Selector.open();  
        // 注册到selector，等待连接  Registers this channel with the given selector, returning a selection key
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
        System.out.println("Server Start----:"+port);
    }  
  
  
    // 监听  
    private void listen() throws IOException {  
        while (true) {  
            // 选择一组键，并且相应的通道已经打开  a blocking operation
            selector.select();
            //阻塞指定的事件
//            selector.select(3000);
            //立即返回
//            selector.selectNow();
//            在A线程中唤醒B线程中阻塞在select上的方法
//            selector.wakeup();
            // 返回此选择器的已选择键集。 (是个成员变量,每次有IO事件的时候会将其放入该Set中,此集合最后需要清除)
            Set<SelectionKey> selectionKeys = selector.selectedKeys();  
            Iterator<SelectionKey> iterator = selectionKeys.iterator();  
            while (iterator.hasNext()) {          
                SelectionKey selectionKey = iterator.next();  
                iterator.remove();
                handleKey(selectionKey);
                try {
                }catch (Exception e){
                    e.printStackTrace();
//                    if (selectionKey!=null){
//                        System.out.println("远程断开了一个连接!");
//                        selectionKey.cancel();
//                        selectionKey.channel().close();
//                    }
                }
            }
            /*或者调用clean最后清除,或者调用remove方法*/
//            selectionKeys.clear();
        }  
    }
    // 处理请求  
    private void handleKey(SelectionKey selectionKey) throws IOException {  
        ServerSocketChannel server = null;
        SocketChannel client = null;  
        String receiveText;  
        String sendText;  
        int count=0;  
        // 有新的连接事件到来
        if (selectionKey.isAcceptable()) {  
            // 返回为之创建此键的通道。  
            server = (ServerSocketChannel) selectionKey.channel();  
            // 接受到此通道套接字的连接。  
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。  
            client = server.accept();  
            // 配置为非阻塞  
            client.configureBlocking(false);
            // 注册到selector，等待连接
            client.register(selector, SelectionKey.OP_READ);  
        } else if (selectionKey.isReadable()) {  //可读事件
            // 返回为之创建此键的通道。  
            client = (SocketChannel) selectionKey.channel();  
            //将缓冲区清空以备下次读取  
            receiveBuffer.clear();
            //读取服务器发送来的数据到缓冲区中  
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
  
    /** 
     * @param args 
     * @throws IOException 
     */  
    public static void main(String[] args) throws IOException {  
        int port = 10099;
        NIOServer server = new NIOServer(port);  
        server.listen();  
    }  
}  