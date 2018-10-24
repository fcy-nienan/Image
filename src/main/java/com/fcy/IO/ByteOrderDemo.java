package com.fcy.IO;

import java.nio.*;
import java.io.*;
import java.util.Arrays;
import java.nio.charset.Charset;
public class ByteOrderDemo {
    public static void main(String[] args) throws IOException {
        byte s1=-1;
        int testtest=0xff;
        int s2=(int)s1;
        int s4=s1;
        int s3=s1&0xff;
        System.out.println(s3);
        System.out.println(s2);
        System.out.println(testtest);
        System.out.println(s1);
        System.out.println(s1&0xff);
        FileInputStream inputStream=new FileInputStream("H:\\keystroe.p12");
        byte[] bytes=new byte[50];
        inputStream.read(bytes);
        for(byte s:bytes){
            System.out.print(Integer.toHexString((int)s));
        }
        System.out.println();
        String ss=new String(bytes);
        char[] chars1=ss.toCharArray();
        System.out.println("Stream");
        for(char aa:chars1){
            System.out.print(Integer.toHexString((int)aa));
        }
        FileReader fileReader=new FileReader("H:\\keystroe.p12");
        char[] chars=new char[50];
        fileReader.read(chars);
        System.out.println();
        System.out.println("Reader");
        for(char c:chars){
            System.out.print(Integer.toHexString((int)c));
            System.out.print(c);
        }
        char name=0x6960;
        System.out.println(name);
        System.out.println(ByteOrder.nativeOrder());
        int x = 0x01020304;

        ByteBuffer bb = ByteBuffer.wrap(new byte[4]);
        bb.asIntBuffer().put(x);
        String ss_before = Arrays.toString(bb.array());

        System.out.println("默认字节序 " +  bb.order().toString() +  ","  +  " 内存数据 " +  ss_before);

        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asIntBuffer().put(x);
        String ss_after = Arrays.toString(bb.array());

        System.out.println("修改字节序 " + bb.order().toString() +  ","  +  " 内存数据 " +  ss_after);
    }
    public static int byteToint(byte[] bytes){
        if(bytes.length==2)
            return bytes[1]&0xff|
                    (bytes[0]&0xff)<<8;
        else if(bytes.length==4)
            return bytes[3]&0xff|
                    (bytes[2]&0xff)<<8|
                    (bytes[1]&0xff)<<16|
                    (bytes[0]&0xff)<<24;
        else
            return 0;
    }
}

