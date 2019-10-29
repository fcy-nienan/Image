package com.fcy.Net.NATTransfromation;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.logging.Logger;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-12  20:08
 */
public class ServerHttpHandler implements com.sun.net.httpserver.HttpHandler {
    private Logger logger=Logger.getLogger(ServerHttpHandler.class.getName());
    private BlockingQueue<String> urlQueue;
    private volatile HashMap<String,Object> urlData;
    private Exchanger<Integer> exchanger;
    private ClientHandler clientHandler;
    private Integer isRead=0;
    private Integer dataLen=0;
    public ServerHttpHandler(Exchanger exchanger,BlockingQueue<String> queue,HashMap<String,Object> hashMap,ClientHandler clientHandler){
        this.exchanger=exchanger;
        this.clientHandler=clientHandler;
        this.urlData=hashMap;
        this.urlQueue=queue;
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestPath = httpExchange.getRequestURI().getPath();
        logger.info("http server get Request Path:" + requestPath);

        clientHandler.setHttpExchange(httpExchange);
        logger.info("将输出流传给ClientHandler");
        urlQueue.add(requestPath);
        logger.info("将需要访问的路径" + requestPath + "加入阻塞队列");
        try{
            logger.info("获取数据长度");
            dataLen = exchanger.exchange(isRead);
            httpExchange.sendResponseHeaders(200, dataLen);
            clientHandler.setHttpOutput(httpExchange.getResponseBody());
            isRead = 1;
            logger.info("发送http头部完成");
            isRead = exchanger.exchange(isRead);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
