package Nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-11  21:41
 */
public class MMAP {
    private static int c,count=0;
    private static long start,end;
    private static String path="G:\\netty.mp4";
    private static String writePath="G:\\ttt.txt";
    private static long writeSize=1024*1024*10;
    public static void main(String[] args) throws Exception {
        BufferRead(path);
        mmapRead(path);
        noBufferRead(path);
//        mmapWrite(writePath);
//        BufferWrite(writePath);
//        noBufferWrite(writePath);

    }
    public static void BufferRead(String path)throws Exception{
        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(path));
        start();
        byte[] bytes=new byte[1024];
        while((c=bufferedInputStream.read(bytes,0,1024))!=-1){
            count+=c;
        }
//        while((c=bufferedInputStream.read())!=-1){
//            count++;
//        }
        bufferedInputStream.close();
        end();
        disResult("fileInputStream");
    }
    public static void mmapRead(String path)throws Exception{
        File file=new File(path);
        long size=file.length();
        FileChannel channel=new RandomAccessFile(file,"r").getChannel();
        start();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        for(int i=0;i<size;i++){
            map.get();
            count++;
        }
        channel.close();
        end();
        disResult("mmapRead");
    }
    public static void noBufferRead(String path)throws Exception{
        FileInputStream fileInputStream=new FileInputStream(path);
        start();
        while((c=fileInputStream.read())!=-1){
            count++;
        }
        fileInputStream.close();
        end();
        disResult("noBufferRead");
    }
    private static void disResult(String goal){
        System.out.println(goal+" cost time:"+(end-start)+"--bytes count:"+count);
        count=0;
    }
    private static void start(){
        start=System.currentTimeMillis();
    }
    private static void end(){
        end=System.currentTimeMillis();
    }
    private static void clearWriteFile() throws IOException {
        File file=new File(writePath);
        System.out.println(file.exists());
        if (file.exists()){
            System.out.println(file.delete());
        }
//        File f=new File(writePath);
//        System.out.println(f.exists());
//        if(!f.exists()){
//            f.createNewFile();
//        }
    }
    public static void BufferWrite(String path)throws Exception{
        BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(path));
        start();
        for(int i=0;i<writeSize;i++){
            outputStream.write(i);
            count++;
        }
        end();
        outputStream.close();
        disResult("BufferedWriter");
        clearWriteFile();
    }
    public static void noBufferWrite(String path)throws Exception {
        FileOutputStream fileOutputStream=new FileOutputStream(path);
        start();
        for(int i=0;i<writeSize;i++){
            fileOutputStream.write(i);
        }
        fileOutputStream.close();
        end();
        disResult("fileOutputStream");
        clearWriteFile();
    }
    public static void mmapWrite(String path)throws Exception{
        RandomAccessFile accessFile=new RandomAccessFile(path,"rw");
        FileChannel channel=accessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, writeSize);
        start();
        for(int i=0;i<writeSize;i++){
            map.put(i,(byte)i);
            count++;
        }

        end();
        channel.close();
        accessFile.close();
        disResult("mmapWrite");
        clearWriteFile();
    }
}
