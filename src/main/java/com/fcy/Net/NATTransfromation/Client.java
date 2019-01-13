package com.fcy.Net.NATTransfromation;





import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class Client {
    private Logger logger=Logger.getLogger(Client.class.getName());
    private Socket client;
    private String remoteHost;
    private int remotePort;
    private String localHost;
    private int localPort;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String procotol="http://";
    public Client(String remoteHost,int remotePort,String localHost,int localPort){
        this.remoteHost=remoteHost;
        this.remotePort=remotePort;
        this.localHost=localHost;
        this.localPort=localPort;
    }
    private void init(){
        client=new Socket();
        try {
            logger.info("开始连接远程服务器:"+remoteHost+":"+remotePort);
            client.connect(new InetSocketAddress(remoteHost,remotePort));
            logger.info("连接成功!");
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            logger.info("连接远程服务器失败!");
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static byte[] inputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] bytes=new byte[1024];
        int len=-1;
        while ((len=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
    public static byte[] getLocalResource(String path){
        byte[] bytes=null;
        try {
            URL url = new URL(path);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            bytes=inputStreamToByte(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bytes;
    }
    private void run(){
        logger.info("send host to server:"+localHost);
        Command command=new Command(1,(procotol+localHost).getBytes());
        Command.encode(command,dataOutputStream);
        logger.info("send port to server:"+localPort);
        Command command1=new Command(2,String.valueOf(localPort).getBytes());
        Command.encode(command1,dataOutputStream);

        Command command2=Command.decode(dataInputStream);
        logger.info("服务器为您分配的外网url为:" + command2.getStringData());
        while (true) {
            String localPath="获取服务器请求资源路径失败";
            try {
                logger.info("等待服务器请求资源");
                Command command3 = Command.decode(dataInputStream);
                localPath = command3.getStringData();
                logger.info("服务器端请求路径:"+localPath);

                logger.info("get http request bytes");
                Command command4=Command.decode(dataInputStream);
                byte[] requestBytes=command4.getData();

//                System.out.println(new String(requestBytes));

                URL url = new URL(localPath);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                int conlen=connection.getContentLength();
                if (conlen!=-1){
                    dataOutputStream.writeInt(200);
                    dataOutputStream.writeInt(conlen);
                    int bufferSize=65535;
                    byte[] bytes=new byte[bufferSize];
                    int total=0;
                    int readed=0;
                    while (true){
                        int ava=Math.min(bufferSize,conlen-total);
                        readed=inputStream.read(bytes,0,ava);
                        dataOutputStream.write(bytes,0,readed);
                        if (readed != -1 && readed != 0) {
                            total+=readed;
                        }
                        if (total==conlen)
                            break;
                    }
                    logger.info("数据发送完毕");
                }else{
                    byte[] bytes=getLocalResource(localPath);
                    Command command31=new Command(4,bytes);
                    logger.info("开始发送本地资源数据到服务器端---数据长度:" + command31.getLen());
                    Command.encode(command31,dataOutputStream);
                    logger.info("数据发送完毕");
                }
            } catch (MalformedURLException e) {
                logger.info("访问路径协议错误!"+localPath);
                String msg="url协议出错";
                Command command3=new Command(400,msg.getBytes());
                Command.encode(command3,dataOutputStream);
                e.printStackTrace();
            } catch (IOException e) {
                logger.info("需要访问的资源不存在!"+localPath);
                String msg="资源不存在";
                Command command3=new Command(404,msg.getBytes());
                Command.encode(command3,dataOutputStream);
                e.printStackTrace();
            }
        }
    }
    public void start(){
        init();
        run();
    }

    public static void main(String[] args) {
        Client client=new Client("127.0.0.1",8989,"127.0.0.1",80);
        client.start();
    }
}
