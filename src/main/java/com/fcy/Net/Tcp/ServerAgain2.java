package com.fcy.Net.Tcp;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-14  13:02
 */
public class ServerAgain2 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println(serverSocket.getInetAddress().getHostAddress());
        Socket socket=serverSocket.accept();
        while (true){
            Thread.sleep(3000);
        }
    }
}
