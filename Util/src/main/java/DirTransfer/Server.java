package DirTransfer;

import CommonUtil.IOUtil;
import Log.log;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/*
 * Author:fcy
 * Date:2020/2/5 13:23
 */
public class Server {
    public static void main(String[] args) {
        String localHost = "127.0.0.1";
        int localPort = 9999;
        String dir = "D:\\Resources\\download";
        Server server = new Server(localHost, localPort, dir);
    }

    private String localHost;
    private int localPort;
    private ServerSocket serverSocket;
    private String dir;

    public Server(String localHost, int localPort, String dir) {
        this.localHost = localHost;
        this.localPort = localPort;
        this.dir = dir;
        init();
        transfer();
    }

    public void init() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.bind(new InetSocketAddress(localHost, localPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class readMonitor implements Runnable {
        private handleClient client;
        private boolean startRead;
        private volatile long bytesNum;
        private Thread thread;
        public readMonitor(handleClient client){
            this.client=client;
            this.thread=new Thread(this);
            this.thread.start();
        }
        @Override
        public void run() {
            System.out.println("start check");
            long lastNum = 0;
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startRead) {
                    System.out.println("start read");
                    long nowNum = bytesNum;
                    if (lastNum == nowNum) {
                        System.out.println("interrupt it ");
                        client.interrupt();
                        break;
                    }
                    lastNum=nowNum;
                }
            }
        }
    }

    private class handleClient extends Thread {
        private Socket client;
        private Random random = new Random();
        private readMonitor readMonitor;

        public handleClient(Socket client) {
            this.client = client;
            this.readMonitor=new readMonitor(this);
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                inputStream = client.getInputStream();
                String fileName = "file" + random.nextInt(100000);
                File file = new File(fileName);
                outputStream = new FileOutputStream(file);
                log.info("get a new file and store as a file:" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                int c;
                byte[] bytes = new byte[2048];

                readMonitor.startRead = true;

                while ( (c = inputStream.read(bytes)) != -1) {
                    readMonitor.bytesNum+=c;
                    outputStream.write(bytes, 0, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("close connection:" + client.getRemoteSocketAddress());
                IOUtil.closeStream(inputStream);
                IOUtil.closeStream(outputStream);
                IOUtil.closeStream(client);
            }
        }
    }

    private void transfer() {
        while (true) {
            Socket client;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            System.out.println("new connection:" + client.getRemoteSocketAddress());
            new handleClient(client).start();
        }
    }
}
