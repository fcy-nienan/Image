package com.fcy.M3u8;

import java.io.*;
import java.util.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-08  15:14
 */
public class Combination {
    public static void main(String[] args) throws IOException {
        Combination combination=new Combination();
        HashMap<String,Integer> map=combination.getPath();
        Set<Map.Entry<String,Integer>> set=map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator=set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Integer> entry=iterator.next();
            String root=entry.getKey();
            int size=entry.getValue();
            String outPath=root+"\\out";
            for(int i=0;i<size;i++){
                String path=root+"\\"+i;
                System.out.println(path);
                byte[] bytes=combination.getContent(path);
                combination.write(bytes,outPath);
            }
            return;
        }
    }
    private byte[] getContent(String path) throws IOException {
        File file=new File(path);
        FileInputStream inputStream=new FileInputStream(file);
        byte[] bytes=new byte[(int) file.length()];
        inputStream.read(bytes);
        return bytes;
    }
    private void write(byte[] bytes,String path) throws IOException {
        RandomAccessFile file=new RandomAccessFile(path,"rw");
        long size=file.length();
        file.seek(size);
        file.write(bytes);
        file.close();
    }
    private HashMap<String,Integer> getPath(){
        HashMap<String,Integer> map=new HashMap<>();
        String root="C:\\Users\\Administrator\\Desktop\\VideoData";
        File file=new File(root);
        File[] files=file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()){
                List<Integer> list=new ArrayList<>();
                String item=file1.getAbsolutePath();
                int size=file1.list().length-1;
                map.put(item,size);
            }
        }
        return map;
    }
}
