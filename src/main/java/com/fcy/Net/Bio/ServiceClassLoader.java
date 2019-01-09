package com.fcy.Net.Bio;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-14  22:39
 */
public class ServiceClassLoader extends ClassLoader {
    private String path;
    public ServiceClassLoader(String path){
        this.path=path;
    }
    public static void main(String[] args) throws IOException {
        Process process=Runtime.getRuntime().exec("pwd");
        BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
        String msg=reader.readLine();
        msg=msg.substring(1,msg.length());
        msg=msg.substring(0,1)+":"+msg.substring(1,msg.length());
        String root=msg.replace("/","\\\\")+"\\\\";
        root+="target\\classes\\";

        ServiceClassLoader loader=new ServiceClassLoader(root);
        Class http=loader.findClass("com.fcy.Net.Bio.Http");
        System.out.println(http.getSimpleName());
    }
    public Class<?> findClass(String name) {
        String pathname = name.replace(".", "/");
        String classPath = path + pathname + ".class";
        InputStream is = null;
        ByteOutputStream outputStream = null;
        try {
            is = new FileInputStream(new File(classPath));
            outputStream = new ByteOutputStream();
            int b = is.read();
            while (b != -1) {
                outputStream.write(b);
                b = is.read();
            }
            byte[] bytes = outputStream.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                is.close();
            } catch (Exception e) {
                outputStream = null;
                is = null;
            }
        }
        return null;
    }
}
