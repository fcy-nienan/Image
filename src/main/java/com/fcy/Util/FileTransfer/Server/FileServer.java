package com.fcy.Util.FileTransfer.Server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class FileServer {
    private static Logger logger = Logger.getLogger(FileServer.class.getName());

    public static void main(String args[]) throws Exception {
        String ip="";
        int port=0;
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress(80));
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("new Connection:"+socket.getRemoteSocketAddress());
        }
    }
}
