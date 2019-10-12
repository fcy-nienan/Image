package com.fcy.Java.ClassLoader;

import com.mysql.jdbc.ConnectionImpl;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ClassLoaderDemo extends ClassLoader{
    private static Logger logger=Logger.getLogger(ClassLoaderDemo.class.getName());
    private String path="H://";
    public Class<?> findClass(String name){
        logger.info("");
        String pathname=name.replace(".","/");
        String classPath=path+pathname+".class";
        InputStream is=null;
        ByteOutputStream outputStream=null;
        try{
            is=new FileInputStream(new File(classPath));
            outputStream=new ByteOutputStream();
            int b=is.read();
            while(b!=-1){
                outputStream.write(b);
                b=is.read();
            }
            byte[] bytes=outputStream.toByteArray();
            System.out.println(bytes.length);
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                is.close();
            }catch (Exception e){
                outputStream=null;
                is=null;
            }
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        ClassLoaderDemo demo=new ClassLoaderDemo();
//        Class clazz;
//        clazz = demo.loadClass("com.fcy.demo.test");
//        System.out.println(clazz);
//        System.out.println(clazz.getClassLoader());
        disPath();
    }
    public static void disPath() throws SQLException {
        System.out.println("启动类加载器加载路径");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("扩展类加载器加载路径:");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("应用类加载器加载路径");
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(Connection.class.getClassLoader());
        System.out.println(ConnectionImpl.class.getClassLoader());
        System.out.println(System.getProperty("jdbc.drivers"));
    }
}
