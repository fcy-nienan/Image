package com.fcy.Net.NATTransfromation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-13  21:26
 */
public class byteT {
    public static void main(String[] args) {

        byte[] bytes=new byte[]{1,2,3,-1,-2,-3};

        ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(bytes);
        for(int i=0;i<bytes.length;i++) {
            System.out.println(arrayInputStream.read());
        }
        System.out.println(arrayInputStream.read());
        FileInputStream inputStream;
        System.out.println(arrayInputStream.read());


    }
    public static void write(int b){
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b));
    }

}
