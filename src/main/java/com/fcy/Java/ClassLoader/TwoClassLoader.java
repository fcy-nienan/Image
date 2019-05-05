package com.fcy.Java.ClassLoader;

import com.fcy.Util.IOUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-05  21:18
 */
public class TwoClassLoader extends ClassLoader {
    private String classPath="H:\\Two";
    public TwoClassLoader(ClassLoader parent){
        super(parent);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className=classPath+ File.separator+name.replace('.',File.separatorChar)+".class";
        try(
                InputStream inputStream=new FileInputStream(className);
        ){
            byte[] bytes= IOUtil.readByteFromStream(inputStream);
            return defineClass(name,bytes,0,bytes.length);
        }catch (IOException e){

        }
        return super.findClass(name);
    }

    @Override
    protected URL findResource(String name) {
        String resourcePath=classPath+File.separator+name;
        File file=new File(resourcePath);
        try {
            return file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return super.findResource(name);
    }
}
