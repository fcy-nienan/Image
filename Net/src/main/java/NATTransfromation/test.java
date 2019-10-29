package com.fcy.Net.NATTransfromation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-13  13:45
 */
public class test {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://127.0.0.1:80/H:/新建文件夹/9-3~9-7.rar");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes=null;
        int conlen = connection.getContentLength();
        int total = 0;
        System.out.println(conlen);
        if (conlen != -1) {
            bytes = new byte[conlen];
            while (total != conlen) {
                int ava = Math.min(inputStream.available(), conlen);
                int c = inputStream.read(bytes, total, ava);
                if (c != -1 && c != 0) {
                    System.out.println("C:"+c);
                    total += c;
//                    System.out.println("len:" + total);
                }
            }
        }
        FileUtil.save(bytes,"D:\\test3.rar");

    }
}
