package com.fcy.Util.FileTransfer.Server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FileServer {
    private static Logger logger = Logger.getLogger(FileServer.class.getName());

    public static void main(String args[]) throws Exception {
        String ip="127.0.0.1";
        int port=8080;
        String path="";
        ThreadPoolExecutor executor=new ThreadPoolExecutor(50,100,0, TimeUnit.SECONDS,new LinkedBlockingQueue());
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ip,port));
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("new Connection:"+socket.getRemoteSocketAddress());
            executor.submit(new ClientHandler(socket));
        }
    }
}
