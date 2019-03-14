package com.fcy.Net.Nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-13  22:52
 */
public class MapperByteBufferDemo {
    public static void main(String[] args) {
        File file=new File("H:\\springboot.keystore");
        long len=file.length();
        byte[] bytes=new byte[(int)len];
        try{
            MappedByteBuffer buffer=new RandomAccessFile(file,"r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY,0,len);
            for(int offset=0;offset<len;offset++){
                byte b=buffer.get();
                bytes[offset]=b;
            }
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
