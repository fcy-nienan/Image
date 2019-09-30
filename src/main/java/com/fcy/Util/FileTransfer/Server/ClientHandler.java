package com.fcy.Util.FileTransfer.Server;

import com.fcy.Java.Serializable.SerializableUtil;
import com.fcy.Util.FileTransfer.Common.CreateFileCommand;
import com.fcy.Util.FileTransfer.Common.IOUtil;
import com.fcy.Util.FileTransfer.Common.ScanNewestFileTask;

import java.io.*;
import java.net.Socket;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private Socket client;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ScanNewestFileTask task;
    private String path;
    private BlockingQueue<File> queue;
    public ClientHandler(Socket socket){
        this.client=socket;
    }
    public ClientHandler(Socket socket, ScanNewestFileTask task, String path, BlockingQueue<File> queue){
        this.client=socket;
        this.queue=queue;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static Logger logger = Logger.getLogger(ClientHandler.class.getName());
    @Override
    public void run() {
        while (true){

        }
    }
}
