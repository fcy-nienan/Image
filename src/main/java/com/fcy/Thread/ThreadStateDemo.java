package com.fcy.Thread;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
* java线程的五种状态
* NEW 调用start方法
* RUNNABLE 可运行状态
* BLOCKED
 * WAITING
 * TIME_WAITING
* TERMINTED
*
* */
public class ThreadStateDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Object obj=new Object();
        Thread t1=new Thread(()->{
            try {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ServerSocket server=new ServerSocket(8099);
                while(true){
                    System.out.println("开启线程");
                    server.accept();
                    System.out.println("连接来临");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Thread t2=new Thread(()->{
            try{
                System.out.println("link before");
                Socket socket=new Socket("localhost",8099);
                System.out.println(socket.getRemoteSocketAddress());
                System.out.println("link after");
                while(true){
                    Thread.sleep(2000);
                    System.out.println("Thread 2:"+socket.getKeepAlive());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        t1.start();

        int i=0;
        while(true){
            Thread.sleep(1000);
            System.out.println(t1.getState());
            if(i==5){
                t2.start();
            }
            i++;
        }
    }
}
