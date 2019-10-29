package com.fcy.Net.NATTransfromation;

import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-13  19:07
 */
public class ServerSocketHandler extends Thread {
    private ThreadPoolExecutor executor=new ThreadPoolExecutor(100,200,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
    private ServerSocket serverSocket;
    private ClientHandler handler;
    private int port;
    public ServerSocketHandler(int port,ClientHandler handler){
        this.port=port;
        this.handler=handler;
    }
    private void init(){
        try {
            serverSocket=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    @Override
    public void run() {
        init();
        while (true) {
            Socket client = null;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
//            try {
//                handler.getClients().put(client);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }
}
