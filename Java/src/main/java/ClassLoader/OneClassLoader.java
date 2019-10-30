package ClassLoader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import CommonUtil.IOUtil;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-05  20:56
 */
public class OneClassLoader extends ClassLoader {
    private String classPath="H:\\One";
    public OneClassLoader(){
        super();
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className=classPath+ File.separator+name.replace('.',File.separatorChar)+".class";
        try(InputStream inputStream=new FileInputStream(className)){
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
