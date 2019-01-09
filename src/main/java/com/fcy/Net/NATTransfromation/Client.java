package com.fcy.Net.NATTransfromation;




import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class Client {
    private Socket client;
    private String remoteHost;
    private int remotePort;
    private String localHost;
    private int localPort;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public Client(String remoteHost,int remotePort,int localPort){
        this.remoteHost=remoteHost;
        this.remotePort=remotePort;
        this.localPort=localPort;
    }
    private void init(){
        client=new Socket();
        try {
            client.connect(new InetSocketAddress(remoteHost,remotePort));
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void run(){
        while (true) {
            try {
                String path=dataInputStream.readUTF();
                System.out.println("得到需要被访问的路径:"+path);

                String localPath="http://127.0.0.1:"+localPort+path;
                System.out.println("开始访问本地资源:"+localPath);
                URL url=new URL(localPath);
                URLConnection connection=url.openConnection();
                InputStream inputStream=connection.getInputStream();
                int len=inputStream.available();
                byte[] bytes=new byte[len];
                inputStream.read(bytes);
                System.out.println("开始发送本地资源数据到服务器端"+len);
                dataOutputStream.writeInt(1);
                dataOutputStream.writeInt(len);
                dataOutputStream.write(bytes);
                System.out.println("发送数据完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void start(){
        init();
        run();
    }

    public static void main(String[] args) {
        Client client=new Client("127.0.0.1",8888,80);
        client.start();
    }
}
