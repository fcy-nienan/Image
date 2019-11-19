package DynamicLoader;

import Common.FileDifferentInfo;
import sun.misc.Launcher;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
public class FcyClassLoader extends ClassLoader {
    private String home;
    public FcyClassLoader(String home){
        this.home=home;
    }
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = null;
        b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        String shortName=fileName.replace(".",File.separator)+".class";
        String path=home+File.separator+shortName;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int nextValue = 0;
        try {
            FileInputStream inputStream=new FileInputStream(path);
            while ( (nextValue = inputStream.read(buffer)) != -1 ) {
                byteStream.write(buffer,0,nextValue);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
