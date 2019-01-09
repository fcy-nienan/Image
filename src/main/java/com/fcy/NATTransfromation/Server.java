package com.fcy.NATTransfromation;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.spark_project.jetty.util.BlockingArrayQueue;
import sun.net.httpserver.HttpServerImpl;
import sun.net.httpserver.HttpsServerImpl;

import java.awt.image.ImageConsumer;
import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static Logger logger=Logger.getLogger(Server.class.getName());
    private ThreadPoolExecutor executor=new ThreadPoolExecutor(100,120,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
    private ServerSocket serverSocket;
    private static BlockingArrayQueue<String> urlQueue=new BlockingArrayQueue<>();
    private static ConcurrentHashMap<String,Object> urlData=new ConcurrentHashMap<>();
    private int port;
    class outerServer implements Runnable{
        @Override
        public void run() {
            ServerSocket serverSocket= null;
            try {
                serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress(80));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Socket socket=serverSocket.accept();
                    executor.submit(new outerHandler(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class outerHandler implements Runnable{
        private Socket socket;
        public outerHandler(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {

        }
    }
    public Server(int port){
        this.port=port;
    }
    public void start(){
        init();
        accept();
    }
    private void init(){
        try {
            serverSocket=new ServerSocket();
        } catch (IOException e) {
            logger.log(Level.WARNING,"创建服务器端套接字失败!"+e.getMessage());
        }
        try {
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            logger.log(Level.WARNING,"绑定本地端口失败!"+e.getMessage());
        }
    }
    private void record(Socket socket){
        logger.info("有客户端连接:");
        logger.info(socket.getInetAddress().getHostAddress());
    }
    private void accept(){
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                record(socket);
                executor.submit(new Handle(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Handle implements Runnable{
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        public Handle(Socket socket){
            init(socket);
        }
        private void init(Socket socket){
            this.socket=socket;
            try {
                this.dataInputStream=new DataInputStream(this.socket.getInputStream());
                this.dataOutputStream=new DataOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            while (true) {
                try {
                    String path=urlQueue.take();
                    System.out.println("发送需要访问的路径");
                    dataOutputStream.writeUTF(path);
                    System.out.println("读取客户端发过来的数据");
                    int type=dataInputStream.readInt();
                    int len=dataInputStream.readInt();
                    byte[] bytes=new byte[len];
                    dataInputStream.read(bytes);

                    System.out.println("数据读取完毕data:"+type+":"+len);
                    urlData.put(path,bytes);
                    System.out.println("开始保存到文件中:");
                    FileUtil.save(bytes,"D:\\test");
                    Thread.sleep(4000);
                } catch (IOException e) {

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        http();
        Server server=new Server(8888);
        server.start();
    }
    public static void http(){
        try {
            HttpServer server =HttpServer.create(new InetSocketAddress(8080),0);
            server.createContext("/",new httpHandler());
            server.setExecutor(null);
            server.start();
        }catch (Exception e){

        }
    }
    static class httpHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String path=httpExchange.getRequestURI().getPath();
            System.out.println("http server get Request Path:"+path);
            urlQueue.add(path);
            while (true){
                Object o=urlData.get(path);
                if (o!=null) {
                    byte[] bytes = (byte[]) o;
                    System.out.println("开始发送数据给客户端"+bytes.length);
                    httpExchange.sendResponseHeaders(200,bytes.length);
                    OutputStream outputStream = httpExchange.getResponseBody();
                    outputStream.write(bytes);
                    outputStream.close();
                    urlQueue.remove(path);
                    return;
                }
            }
        }
    }
}
