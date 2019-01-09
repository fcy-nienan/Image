package com.fcy.Net.Tcp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-07  14:38
 */
public class Client implements Runnable{
    private Socket socket;
    private String host="192.168.43.176";
    private int port=8989;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public Client(int x){

    }
    public Client(){
        try {
            this.socket=new Socket(host,port);
//            socket.bind();
//            socket.connect();
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println(e.getClass()+":连接出错!");
        }
    }
    public void run(){
        while(true){
            String msg=getContent("Thread.txt");
            msg=String.valueOf(socket.getPort());
            send(msg);
            try {
                Thread.sleep(50000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }
    private String getContent(String path){
        File file=new File("H:\\"+path);
        byte[] bytes=new byte[(int) file.length()];
        try {
            FileInputStream inputStream=new FileInputStream(file);
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
    private void send(String msg){
        System.out.println("send msg");
        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getClass()+":发送数据出错!");
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(200,400,0,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for(int i=0;i<100;i++) {
            Client client=new Client();
            executor.submit(client);
        }
    }
}
