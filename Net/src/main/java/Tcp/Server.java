package com.fcy.Net.Tcp;

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
 * @date: 2018-10-07  14:28
 */
public class Server {
    public static void main(String[] args) {
        Server server=new Server();
        server.listen();
    }
    private ThreadPoolExecutor executor;
    private ServerSocket serverSocket;
    private String host="127.0.0.1";
    private int port=8080;

    public Server(String host,int port){
        executor=new ThreadPoolExecutor(200,400,0,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        try {
            serverSocket.bind(new InetSocketAddress(this.host,this.port));
            serverSocket=new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server(){
        System.out.println(this.host);
        System.out.println("sdf");
//        this(host,port);
    }
    public void listen(){
        while(true){
            try {
                System.out.println("等待客户端连接");
                Socket client=serverSocket.accept();
                System.out.println("客户端连接:"+client.getPort());
                executor.submit(new clientThread(client));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class clientThread implements Runnable{
        private Socket client;
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        public clientThread(Socket client){
            this.client=client;
        }
        public void init()  {
            try {
                this.bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
                this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            } catch (IOException e) {
                System.out.println(e.getClass()+":客户端socket错误!");
            }
        }
        @Override
        public void run(){
            init();
            while(true) {
                try {
//                    System.out.println("deal client:"+client.getPort());
//                    String command = bufferedReader.readLine();
//                    System.out.println("数据长度:"+command.length());
//                    System.out.println("command:" + command);
//                    String result = getContent(command);
//                    bufferedWriter.write(result);
                    Thread.sleep(5000000);
//                } catch (IOException e) {
//                    System.out.println("连接中断");
//                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private String getContent(String path){
            File file=new File("G:\\"+path);
            byte[] bytes=new byte[(int) file.length()];
            try {
                FileInputStream inputStream=new FileInputStream(file);
                inputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String(bytes);
        }
    }
}
