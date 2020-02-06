package DirTransfer;

import CommonUtil.IOUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * Author:fcy
 * Date:2020/2/5 13:24
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        String dir = "D:\\Resources\\upload\\";
        String remoteHost = "127.0.0.1";
        int remotePort = 9999;
        String localHost = "127.0.0.1";
        int localPort = 8888;
        Client client = new Client(dir, remoteHost, localHost, remotePort, localPort);

    }

    private String dir;
    private boolean recursive = false;
    private long scanTimeout = 5000;
    private boolean autoCreate = true;
    private String remoteHost;
    private int remotePort;
    private String localHost;
    private int localPort;
    private Socket socket;
    private int reconnectTimes;
    private int maxReconnectTimes = 10;
    private FileMonitor fileMonitor;
    private BlockingQueue<FileMonitor.ModifiedBean> modifiedQueue;

    public Client(String dir, String remoteHost, String localHost, int remotePort, int localPort) {
        this.dir = dir;
        this.remotePort = remotePort;
        this.remoteHost = remoteHost;
        this.localHost = localHost;
        this.localPort = localPort;
        checkLocalCondition();
        this.modifiedQueue = new LinkedBlockingQueue<>();
        this.fileMonitor = new FileMonitor(modifiedQueue, dir, scanTimeout, recursive);
        init();
        this.fileMonitor.start();
        transfer();
    }

    private void transfer() {
        while (true) {
            OutputStream outputStream = null;
            BufferedInputStream bufferedInputStream = null;
            String transferFile = null;
            try {
                FileMonitor.ModifiedBean modifiedBean = modifiedQueue.take();
                log.info("start transfer a new file:" + modifiedBean);
                outputStream = socket.getOutputStream();
                transferFile = modifiedBean.getPath();
                FileInputStream fileInputStream = new FileInputStream(transferFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
            } catch (InterruptedException e) {
                log.warn("client has been interrupted!");
                e.printStackTrace();
            } catch (IOException e) {
                log.warn("init stream failed!");
                e.printStackTrace();
            }
            int c;
            byte[] bytes = new byte[2048];
            try {
                while ((c = bufferedInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, c);
                }
                log.info("transfer finished!" + transferFile);
            } catch (IOException e) {
                log.warn("write bytes failed!");
                e.printStackTrace();
            }
        }
    }

    private void init() {
        socket = new Socket();
        boolean bindState = false;
        int newPort = localPort;
        int bindFailedTimes = 5;
        int bindTimes = 0;
        while (!bindState && bindTimes < bindFailedTimes) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                socket.bind(new InetSocketAddress(localHost, newPort));
                log.info("bind successfully!" + localHost + ":" + newPort);
                bindState = true;
            } catch (IOException e) {
                e.printStackTrace();
                newPort++;
                bindTimes++;
                log.warn("bind port failed and reselect a port!" + newPort);
            }
        }
        boolean connectState = false;
        if (bindState) {
            while (!connectState && reconnectTimes < maxReconnectTimes) {
                try {
                    socket.connect(new InetSocketAddress(remoteHost, remotePort));
                    connectState = true;
                } catch (IOException e) {
                    log.warn("connect remote host [" + remoteHost + ":" + remotePort + " ] failed and reconnect -- times:" + reconnectTimes);
                    reconnectTimes++;
                }
            }
        } else {
            log.error("bind failed!");
        }
        if (connectState) {
            log.info("connect remote host successfully! [" + remoteHost + ":" + remotePort + "]");
        } else {
            log.error("connect error and tried max times!");
            throw new IllegalStateException("connect failed!");
        }
    }

    private void checkLocalCondition() throws IllegalArgumentException {
        File file = new File(dir);
//        先检查是否存在再检查是否是目录
        if (!file.exists()) {
            log.warn("local dir is not exists!" + dir);
            if (!autoCreate) {
                throw new IllegalArgumentException("path is not exists!" + dir);
            }
            if (file.mkdirs()) {
                log.warn("local path is not exists and auto-create it !" + dir);
            } else {
                throw new IllegalArgumentException("local path is not exists and auto-create failed!" + dir);
            }
        }
        if (!file.isDirectory()) {
            log.warn("local path is not a dir!" + dir);
            throw new IllegalArgumentException("path is not a dir!" + dir);
        }
    }

}
