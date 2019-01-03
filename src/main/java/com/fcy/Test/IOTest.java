package com.fcy.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-30  8:45
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        URL url=new URL("http://www.baidu.com");
        URLConnection connection=url.openConnection();
//        connection.connect();
//        System.out.println(connection.getContent());
//        InputStream outputStream=connection.getInputStream();
        int len=connection.getContentLength();
        System.out.println(len);
//        byte[] bytes=new byte[len];
//        outputStream.read(bytes);
//        String content=new String(bytes);
//        System.out.println(content);
    }
}
