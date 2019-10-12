package com.fcy.Net.Udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  15:44
 */
public class Server {
    public static void main(String[] args) throws Exception {
        String sendMsg="Hello World";
        byte[] bytes=new byte[1024];
        DatagramSocket datagramSocket=new DatagramSocket(new InetSocketAddress(8080));
        DatagramPacket packet=new DatagramPacket(bytes,1024);
        System.out.println("listening client connecting...");
        while (true){
            datagramSocket.receive(packet);
//            System.out.println("received data from client");
//            System.out.println(packet.getAddress());
//            System.out.println(packet.getPort());
            System.out.println(new String(packet.getData(),packet.getLength()));
            DatagramPacket packet1=new DatagramPacket(sendMsg.getBytes(),sendMsg.length(),packet.getAddress(),packet.getPort());
            datagramSocket.send(packet1);
            Thread.sleep(3000);
        }
    }
}
