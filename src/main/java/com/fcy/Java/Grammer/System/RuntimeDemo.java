package com.fcy.Java.Grammer.System;

import lombok.val;

import java.io.File;

/**
 * @descripiton:
 * windows下运行需要用cmd /c
 * File的toString方法返回的是newFile的时候传入的路径参数
 * @author: fcy
 * @date: 2019-03-06  1:20
 */
public class RuntimeDemo {
    public static void main(String[] args) {
        File file1=new File("H:\\wordCount.txt");
        File file2=new File("H:\\fcy\\");
        System.out.println("mv "+file1+" "+file2);
        System.out.println(file1.toString());

        Process process=null;
        try{
            process=Runtime.getRuntime().exec("cmd /c move "+file1+" "+file2);
            process.waitFor();
            val outputStream = process.getOutputStream();
            val inputStream = process.getInputStream();
            byte[] bytes=new byte[20];
            outputStream.write(bytes);
            System.out.println(new String(bytes));
            byte[] bytes1=new byte[20];
            inputStream.read(bytes1);
            System.out.println(new String(bytes1,"gbk"));
            val errorStream = process.getErrorStream();
            byte[] bytes2=new byte[20];
            errorStream.read(bytes2);
            System.out.println(new String(bytes2,"gbk"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
