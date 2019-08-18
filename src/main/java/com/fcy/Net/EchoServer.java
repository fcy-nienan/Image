package com.fcy.Net;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-08  23:21
 */
public class EchoServer {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(100,200,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        ServerSocket socket=new ServerSocket(9988,50);
        while(true){
            Socket client=socket.accept();
            System.out.println("client connectioned!");
            executor.submit(new handler(client));
        }
    }
    static class handler implements Runnable{
        private Socket socket;
        private BufferedReader reader;
        private BufferedWriter writer;
        public handler(Socket s){
            this.socket=s;
        }
        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line=null;
                while(true) {
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        if(line.equals("exit")){
                            System.out.println(socket.getRemoteSocketAddress()+"exit!");
                            return;
                        }
                        writer.write(line);
                        writer.flush();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
