package com.fcy.Thread;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
* java线程的六种状态
* NEW 调用start方法
* RUNNABLE 可运行状态
* BLOCKED   waiting for a monitor lock
 * WAITING  一个线程处于WAITING状态，代表一个线程处于等待其他线程的一个特定的操作
 * 比如因为调用wait方法而处于wait状态代表该线程等待其他线程调用notify或者notifyAll方法
 * 因为调用join方法而处于wait状态代表该线程等待其他线程执行结束
 *
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
            synchronized (obj) {
                obj.notify();
            }
            try{
                Thread.sleep(4000);
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
            if(i==8){
                t2.start();
            }
            i++;
        }
    }
}
