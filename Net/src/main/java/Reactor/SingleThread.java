package Reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class SingleThread implements Runnable {
    public static Selector selector;
    public static ServerSocketChannel serverSocket;
    public SingleThread() throws Exception {
        SelectionKey sk =
                serverSocket.register(selector,
                        SelectionKey.OP_ACCEPT);
        // attach Acceptor 处理新连接
        sk.attach(new Acceptor());
    }


    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    it.remove();
                    //分发事件处理
                    dispatch((SelectionKey) (it.next()));
                }
            }
        } catch (IOException ex) {
            //do something
        }
    }


    void dispatch(SelectionKey k) {
        // 若是连接事件获取是acceptor
        // 若是IO读写事件获取是handler
        Runnable runnable = (Runnable) (k.attachment());
        if (runnable != null) {
            runnable.run();
        }
    }


    /**
     * 连接事件就绪,处理连接事件
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {// 注册读写
                    new Handler(c, selector);
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * 处理读写业务逻辑
     */
    class Handler implements Runnable {
        public static final int READING = 0, WRITING = 1;
        int state;
        final SocketChannel socket;
        final SelectionKey sk;
        public Handler(SocketChannel socket, Selector sl) throws Exception {
            this.state = READING;
            this.socket = socket;
            sk = socket.register(selector, SelectionKey.OP_READ);
            sk.attach(this);
            socket.configureBlocking(false);
        }


        @Override
        public void run() {
            if (state == READING) {
                read();
            } else if (state == WRITING) {
                write();
            }
        }


        private void read() {
            process();
            //下一步处理写事件
            sk.interestOps(SelectionKey.OP_WRITE);
            this.state = WRITING;
        }


        private void write() {
            process();
            //下一步处理读事件
            sk.interestOps(SelectionKey.OP_READ);
            this.state = READING;
        }


        /**
         * task 业务处理
         */
        public void process() {
            //do something
        }
    }
}