package NATTransfromation;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import lombok.Getter;
import lombok.Setter;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-12  15:52
 */
public class ClientHandler implements Runnable{
    private Logger logger=Logger.getLogger(ClientHandler.class.getName());
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private BlockingQueue<String> urlQueue;
    private HashMap<String,Object> urlData;
    private String visitPath;
    private HttpExchange httpExchange;
    private OutputStream httpOutput;
    private volatile int dataLen=0;
    private int isRead=0;
    private Exchanger<Integer> exchanger;
    public ClientHandler(Exchanger exchanger,String path,Socket socket,BlockingQueue<String> queue,HashMap<String,Object> hashMap){
        this.exchanger=exchanger;
        this.visitPath=path;
        this.urlData=hashMap;
        this.urlQueue=queue;
        init(socket);
    }
    public ClientHandler(String visitPath, Socket socket){
        this.visitPath=visitPath;
        init(socket);
    }
    public void setHttpExchange(HttpExchange exchange){
        this.httpExchange=exchange;
    }
    public int getDataLen(){
        return this.dataLen;
    }
    public void setHttpOutput(OutputStream output){
        this.httpOutput=output;
    }
    private void init(Socket socket){
        this.socket=socket;
        try {
            this.dataInputStream=new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream=new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            logger.info("获取用户流失败!"+e.getMessage());
            System.exit(0);
        }
    }
    private byte[] getHttpBytes(){
        byte[] allData=null;
        byte[] space=" ".getBytes();
        byte[] line="\r\n".getBytes();
        byte[] colon=":".getBytes();

        String protocol=httpExchange.getProtocol();
        String method=httpExchange.getRequestMethod();
        String url=httpExchange.getRequestURI().getPath();
        allData=BaseUtil.addBytes(method.getBytes(),space,url.getBytes(),space,protocol.getBytes(),line);
        Headers headers=httpExchange.getRequestHeaders();
        for (Map.Entry<String, List<String>> stringListEntry : headers.entrySet()) {
            String key=stringListEntry.getKey();
            StringBuilder values=new StringBuilder();
            List<String> value=stringListEntry.getValue();
            for (String s : value) {
                values.append(s);
            }
            allData=BaseUtil.addBytes(allData,key.getBytes(),colon,space,values.toString().getBytes(),line);
        }
        allData=BaseUtil.addBytes(allData,line,line);

//        byte[] bodys=new byte[0];
//        try {
//            bodys= IOUtils.toByteArray(httpExchange.getRequestBody());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        allData=BaseUtil.addBytes(allData,bodys);
        return allData;
    }
    @Override
    public void run() {
        logger.info("获取用户端口信息");

        Command command=Command.decode(dataInputStream);
        String clientIp=command.getStringData();

        Command command1=Command.decode(dataInputStream);
        int clientPort=Integer.parseInt(command1.getStringData());

        logger.info("用户网络地址信息:"+clientIp+":"+clientPort);

        Command.encode(new Command(3,visitPath),dataOutputStream);
        logger.info("向用户发送外网访问路径:"+visitPath);

        while (true) {
            try {
                String path=urlQueue.take();
                String clientPath=clientIp+":"+clientPort+path;

                logger.info("send the request path of client:"+clientPath);
                Command command3 = new Command(4, clientPath);
                Command.encode(command3, dataOutputStream);

                logger.info("get client http bytes");
                byte[] allData=getHttpBytes();

                logger.info("send http bytes to user");
                dataOutputStream.writeInt(200);
                dataOutputStream.writeInt(allData.length);
                dataOutputStream.write(allData);


                logger.info("开始读取用户发过来的数据");
                try {
                    int tag = dataInputStream.readInt();
                    int len = dataInputStream.readInt();

                    dataLen=len;
                    exchanger.exchange(dataLen);
                    isRead=exchanger.exchange(isRead);
                    if (isRead==1) {
                        int total = 0, c = 0;
                        int bufferSize = 65536;
                        byte[] bytes = new byte[bufferSize];
                        while (total < len && total != len) {
                            int ava = Math.min(bufferSize, dataInputStream.available());
                            c = dataInputStream.read(bytes, 0, ava);
                            httpOutput.write(bytes, 0, c);
                            total += c;
                        }
                        httpOutput.close();
                    }
                    logger.info("send data to client finished");
                }catch (IOException e){
                    e.printStackTrace();
                }
//                urlData.put(path, command4.getData());
//                logger.info("开始保存到文件中:");
//                FileUtil.save(command4.getData(), "D:\\test");
            }catch (InterruptedException e){
                logger.info("程序异常退出,请联系开发人员!"+e.toString());
                System.exit(0);
            }catch (IOException e){
                logger.info("IO异常:"+e.toString());
            }
        }
     }
}
