package com.fcy.Net.HalfNio;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-03  11:00
 */
public class NioClient {
    public static void main(String args[]) throws Exception {
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<444;i++){
            service.submit(new Client());
        }
        service.shutdown();
    }
    public static void send()throws Exception{
        Socket socket=new Socket("127.0.0.1",8778);
        OutputStream stream=socket.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(stream));
        while(true){
            Thread.sleep(1000);
            writer.append(Thread.currentThread().getId()+":Hello World!");
            writer.flush();
        }
    }
    static class Client implements Runnable{
        @Override
        public void run() {
            try {
                send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
