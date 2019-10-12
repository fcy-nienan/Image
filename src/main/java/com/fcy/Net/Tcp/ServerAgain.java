package com.fcy.Net.Tcp;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  15:33
 */
public class ServerAgain {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1",8080));
        System.out.println(serverSocket.getInetAddress().getHostAddress());
        Socket socket=serverSocket.accept();
        while (true){
            Thread.sleep(3000);
        }
    }
}
