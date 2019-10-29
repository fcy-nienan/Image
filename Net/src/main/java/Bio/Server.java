package com.fcy.Net.Bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-10  23:40
 */
public class Server {
    private ServerSocket serverSocket;
    private ThreadPoolExecutor executor;
    private int coreThread;
    private int maxThread;
    public Server(){
        try {
            this.serverSocket=new ServerSocket();
            this.executor=new ThreadPoolExecutor(coreThread,maxThread,0,TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void init(){
        try {
            this.serverSocket.bind(new InetSocketAddress("120.79.158.25",80));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen(){
        init();
        ServerSocket server=this.serverSocket;
        while(true){
            try {
                Socket socket=server.accept();
                executor.submit(new Handler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
