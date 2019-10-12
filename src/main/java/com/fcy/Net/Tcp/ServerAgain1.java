package com.fcy.Net.Tcp;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  23:43
 */
public class ServerAgain1 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress("192.168.43.176",8080));
        System.out.println(serverSocket.getInetAddress().getHostAddress());
        Socket socket=serverSocket.accept();
        while (true){
            Thread.sleep(3000);
        }
    }
}
