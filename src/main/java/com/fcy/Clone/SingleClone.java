package com.fcy.Clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class SingleClone implements Serializable {
    public int x=10;
    public int y=20;
    private static volatile SingleClone singleClone;
    /*private Single(){

    }*/
    public static SingleClone getSingleClone(){
        if(singleClone ==null){
            synchronized (SingleClone.class){
                if(singleClone ==null){
                    singleClone =new SingleClone();
                }
            }
        }
        return singleClone;
    }
    /**
    *@descripiton  如果在序列化时定义了readResolve()这个方法，
     * 则在反序列化时直接返回此方法指定的对象，而不需要单独再创建新的对象
     * 可以解决单例下反序列化的漏洞
    */
//    public Object readResolve(){
//        return singleClone;
//    }
    public static void main(String[] args)throws Exception {
        SingleSerializableCloneDestroy();

    }
    /**
     * 破坏单例模式
     * 通过对象克隆
     * */

    public static void SingleSerializableCloneDestroy()throws Exception {
        SingleClone singleClone =SingleClone.getSingleClone();

        ByteArrayOutputStream byteOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream outputStream=new ObjectOutputStream((byteOutputStream));
        outputStream.writeObject(singleClone);
        byte[] bytes;
        bytes=byteOutputStream.toByteArray();


        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream=new ObjectInputStream(byteArrayInputStream);
        Object obj=inputStream.readObject();
        SingleClone singleClone1 =(SingleClone)obj;

        System.out.println(singleClone == singleClone1);
        System.out.println(SingleClone.singleClone == singleClone1);
        System.out.println(SingleClone.singleClone == singleClone);
        singleClone1.x=100;
        singleClone1.y=200;
        singleClone.x=300;
        singleClone.y=400;


        System.out.println(singleClone.x+"single"+ singleClone.y);
        System.out.println(singleClone1.x+"single"+ singleClone1.y);
    }
}

