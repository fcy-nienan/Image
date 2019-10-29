package com.fcy.Net.Netty.Echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  18:21
 */
public class BioEchoClient {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    public static void main(String[] args) {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void start() throws InterruptedException {
        reconnect();
        int x=0;
        String msg;
        int readCount=0;
        while(true){
            Thread.sleep(3000);
            try {
                beforeWrite();
                writer.write("Hello World:"+x);
                writer.newLine();
                writer.flush();
                writeCompleted();
//                writer.close();
                x++;
                beforeRead();
                while((msg=reader.readLine())!=null){
                    System.out.println(msg);
                }
                readCompleted();
            }catch(SocketTimeoutException ee){
                readCount++;
                System.out.println("Exception:"+ee.getMessage());
                if (readCount>5)
                    reconnect();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName()+e.getMessage());
                reconnect();
            }
        }
    }
    public static void reconnect() throws InterruptedException {
        System.out.println("start reconnect...");
        Thread.sleep(2000);
        try {
            socket=new Socket();
            socket.setSoTimeout(5000);//设置读取数据超时时间为5秒
            socket.setTcpNoDelay(true);//send立刻发送，不缓存，关闭Negale算法
            socket.setKeepAlive(true);
            socket.setReuseAddress(true);
            socket.setSoLinger(true,3);//调用close时3秒后关闭底层socket
            socket.connect(new InetSocketAddress("localhost",8889));
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("connected successful...");
        } catch (IOException e) {
            try {
                socket.close();
                reader.close();
                writer.close();
            } catch (Exception e1) {
            }
            System.out.println("connect failure...");
        }
    }
    public static void beforeWrite(){
        System.out.println("before write...");
    }
    public static void writeCompleted() throws IOException {
        System.out.println("write completed...");
        writer.close();
    }
    public static void beforeRead(){
        System.out.println("before read...");
    }
    public static void readCompleted() throws IOException {
        System.out.println("read completed...");
        reader.close();
    }
}
