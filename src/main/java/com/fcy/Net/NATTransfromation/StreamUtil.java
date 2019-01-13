package com.fcy.Net.NATTransfromation;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-13  19:51
 */
public class StreamUtil {
    public static void copy(InputStream inputStream, OutputStream outputStream){
        DataInputStream dataInputStream=new DataInputStream(inputStream);
        try {
            int tag = dataInputStream.readInt();
            int len = dataInputStream.readInt();
            int bufferSize = 65535;
            byte[] bytes = new byte[bufferSize];
            int total = 0;
            int c;
            while (true) {
                int onceRead=Math.min(bufferSize,len-total);
                c = dataInputStream.read(bytes, 0, onceRead);
                outputStream.write(bytes,0,c);
                if (c != 0)
                    total += c;
                if (total == len)
                    break;
            }
        }catch (IOException e){

        }
    }

    public static void main(String[] args) {
        System.out.println(100%60);
    }
}
