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

    public static void main (String[] args) throws ClassNotFoundException {
        FcyClassLoader classLoader=new FcyClassLoader("D:\\classes\\");
        Class<?> aClass = classLoader.loadClass(classLoader.home);
    }
    private List<Class<?>> loadClass(ClassLoader classLoader,String src){
        List<Class<?>> list=new ArrayList<>();
        File[] files=new File(src).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".class")) {
                    try {
                        String regular = file.getName().replace(home, "").replace(".class", "");
                        Class clazz = classLoader.loadClass(regular);
                        list.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
        return list;
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
