package NATTransfromation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Server {
    private static Logger logger = Logger.getLogger(Server.class.getName());
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 120, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private ServerSocket serverSocket;
    private int port;
    private ConcurrentHashMap<String, ClientHandler> handleConcurrentHashMap = new ConcurrentHashMap<>();
    private static final String[] paths = new String[]{"/fcy", "/kkk", "/bbb"};
    private static int x = 0;

    public String getRandomPath() {
        return paths[x++];
    }

    public int getRandomPort() {
        Random random = new Random();
        return random.nextInt(1000) + 1024;
    }

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        init();
        accept();
    }

    private void init() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            logger.info("创建服务器端套接字失败!" + e.getMessage());
            System.exit(0);
        }
        try {
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            logger.info("绑定本地端口失败!" + port + e.getMessage());
            System.exit(0);
        }
        logger.info("绑定本地端口成功:" + port);
    }

    private String record(Socket socket) {
        String host = socket.getInetAddress().getHostAddress();
        logger.info("客户端连接:" + host);
        return host;
    }

    private void accept() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                String host = record(socket);


                BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();
                HashMap<String, Object> urlData = new HashMap<>();
                String localIp = getLocalIp();
                int port = getRandomPort();
                String visitPath = localIp + ":" + port;
//
                Exchanger exchanger = new Exchanger();
                ClientHandler handle = new ClientHandler(exchanger, visitPath, socket, urlQueue, urlData);
                logger.info("为用户创建http server:" + visitPath);
                HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
                httpServer.createContext("/", new ServerHttpHandler(exchanger, urlQueue, urlData, handle));
                httpServer.setExecutor(null);
                httpServer.start();
//
//                handleConcurrentHashMap.put(host,handle);
//                logger.info("为用户创建http server:"+visitPath);
//                ClientHandler clientHandler=new ClientHandler(visitPath,socket);
//                ServerSocketHandler serverSocketHandler=new ServerSocketHandler(port,clientHandler);
//                serverSocketHandler.start();

                executor.submit(handle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8989);
        server.start();

    }

    private String getLocalIp() {
        String localIp = null;
        try {
            localIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return localIp;
    }
}
