package com.fcy.Java.ClassLoader;

import com.mysql.jdbc.JDBC4Connection;
import org.apache.spark.sql.sources.In;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @descripiton:
 * Class.forName和loadClass都会走双亲委托模型,我之前还以为Class.forName不走这条路的
 * ClassLoader的getResource是从该类加载器的类路径加载资源文件的,同样走双亲委托模型,先从父类加载器
 * 的类路径寻找资源文件,然后再从自己的类路径加载资源文件(前提是你重写了findResource方法)
 *
 * 线程上下文加载器设置为系统类加载器,那么BootStrap中委托给系统类加载器后是否还要走一次双亲委托机制?还是直接加载?
 * @author: fcy
 * @date: 2019-05-03  11:29
 */
public class DisClassLoader {
    public static void main(String[] args) throws Exception {
        DriverManager dirverManager;
        OneClassLoader oneClassLoader = new OneClassLoader();
        TwoClassLoader twoClassLoader = new TwoClassLoader(oneClassLoader);
        System.out.println(oneClassLoader.getParent());
        System.out.println(twoClassLoader.getParent());
        String outerClassName = "com.classloader.demo.test";
        Class clazz = null;
        clazz = twoClassLoader.loadClass(outerClassName);
//        clazz=Class.forName(outerClassName,true,twoClassLoader);

        URL url = oneClassLoader.getResource("/ttt.txt");
//        System.out.println(url.getPath());
        InputStream inputStream = url.openStream();
        byte[] bytes = new byte[1024];
        int c = inputStream.read(bytes);
        System.out.println(new String(bytes, 0, c));
        Object o = null;
        try {
            o = clazz.newInstance();
            Method method = o.getClass().getDeclaredMethod("dis");
            method.invoke(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
