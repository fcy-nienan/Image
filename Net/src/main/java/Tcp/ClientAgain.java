package com.fcy.Net.Tcp;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-14  0:30
 */
public class ClientAgain {
    public static void main(String[] args)throws Exception {
        Socket socket=new Socket();
        socket.bind(new InetSocketAddress(21213));
        socket.connect(new InetSocketAddress(8888));
        while (true){
            System.out.println(socket.getRemoteSocketAddress());
            Thread.sleep(3000);
            socket.close();
        }
    }
}
