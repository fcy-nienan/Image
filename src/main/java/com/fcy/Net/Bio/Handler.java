package com.fcy.Net.Bio;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-10  23:42
 */
public class Handler implements Runnable{
    private Socket client;
    private BufferedReader reader;
    private OutputStream outputStream;
    private Mapper mapper;
    public Handler(Socket socket){
        this.client=socket;
        this.mapper=new Mapper();
    }
    public void init(){
        try {
            this.reader=new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
            this.outputStream=this.client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        String url=null;
        List<String> headers=null;
        StringBuilder bodys=null;
        try {
            url=reader.readLine();
            headers=new ArrayList<>();
            String head=null;
            while((head=reader.readLine())!="\r\n"){
                headers.add(head);
            }
            bodys=new StringBuilder();
            String body=null;
            while(((body=reader.readLine())!=null)){
                bodys.append(body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Http http=new Http(url,headers,bodys.toString());
        Request request=new Request(http);
        Response response=new Response(outputStream);
        RequestWrapper wrapper=new RequestWrapper(request,response);
        mapper.put(wrapper);
    }
}
