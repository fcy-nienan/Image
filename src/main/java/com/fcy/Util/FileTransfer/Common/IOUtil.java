package com.fcy.Util.FileTransfer.Common;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

public class IOUtil {
    private static Logger logger = Logger.getLogger(IOUtil.class.getName());

    public static void main(String args[]) throws Exception {
        HashMap<String,String> map;
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));
        System.out.println(Integer.MAX_VALUE/1024/1024);
        String s="";
        s.split(" ");
        File file=new File("F:\\Resources\\Linux ISO\\cn_windows_10_consumer_edition_version_1903_updated_june_2019_x64_dvd_cedfd58d.iso");
        System.out.println(file.length());
        System.out.println(Integer.MAX_VALUE);
        int len= (int) file.length();
        System.out.println(len);
    }
    public static byte[] getFileStreamBytes(String path) throws IOException {
        File file=new File(path);
        byte[] bytes=new byte[(int) file.length()];
        FileInputStream inputStream=new FileInputStream(file);
        inputStream.read(bytes);
        closeStream(inputStream);
        return bytes;
    }
    public static void copyStream(InputStream inputStream,OutputStream outputStream) throws IOException {
        int c;
        byte[] bytes=new byte[2048];
        while ((c=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,c);
        }
    }
    public static boolean checkDirPath(String path){
        File file=new File(path);
        if (SystemUtil.isWindows()){
            return checkWindowPath(path);
        }else if (SystemUtil.isLinux()){
            return checkLinuxPath(path);
        }else{
            return false;
        }
    }
    public static void closeStream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                closeable=null;
            }
        }
    }
    private static boolean checkLinuxPath(String path){
        if (path.startsWith("/")){
            return true;
        }else{
            return false;
        }
    }
    private static boolean checkWindowPath(String path){
        if (path!=null){
            int len=path.length();
            if (len==0)return false;
            char one=path.charAt(0);
            if (BinaryUtil.isLetter(one)){

            }
        }
        if (path.startsWith("/")){
            return true;
        }else{
            return false;
        }
    }
    public static void save(byte[] bytes,String path){
        File file=new File(path);
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeStream(fileOutputStream);
        }
    }
}
