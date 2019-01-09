package com.fcy.Java.Serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableUtil {
    public static void main(String[] args) {
        List<Object> all=new ArrayList<>();
        for(int i=0;i<10;i++) {
            all.add(new Object());
        }
        byte[] bytes=writeObject(all);
        System.out.println(bytes.length);
        Object obj=readObject(bytes);
        List<Object> mains=(ArrayList<Object>)obj;
        mains.forEach(e->{
            System.out.println(e.toString());
        });
    }
//    序列化list集合,集合中的数据也必须实现序列化接口，要不然会抛出异常
//    从序列化的byte数组中构造对象
    public static Object readObject(byte[] bytes){
        ObjectInputStream is;
        ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
        Object object=null;
        try {
            is = new ObjectInputStream(inputStream);
            object=is.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
//    将对象序列化为byte数组
    public static byte[] writeObject(Object obj){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream outputStream=null;
        byte[] bytes=null;
        try {
            outputStream=new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(obj);
            bytes=byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<bytes.length;i++){
            System.out.print(bytes[i]+" ");
        }
        return bytes;
    }
}
