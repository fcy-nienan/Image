package com.fcy.Net.Udp;

import java.net.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  15:44
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String sendMsg="Hello ! I am a Client!";
        byte[] bytes=new byte[1024];
        DatagramSocket socket=new DatagramSocket(null);
        socket.bind(new InetSocketAddress(21213));
        DatagramPacket packet=new DatagramPacket(sendMsg.getBytes(),sendMsg.length(),InetAddress.getLocalHost(),8080);
        DatagramPacket receivePacket=new DatagramPacket(bytes,1024);
        while (true){
            socket.send(packet);
            socket.receive(receivePacket);
            System.out.println(new String(bytes,0,receivePacket.getLength()));
            Thread.sleep(3000);
        }
    }
}
