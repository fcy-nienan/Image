package Advanced.Concurrent.Thread;

import ThreadUtil.TP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class TwoThreadAppendFile {
    public static void main (String args[]) throws FileNotFoundException {
        ThreadPoolExecutor tp=TP.getTPE();
        int threadCount=10;
        int writeCount=100000;
        String content="write content"+System.getProperty("line.separator");
        FileOutputStream outputStream=new FileOutputStream(System.getProperty("user.home")+ File.separator+"command");
        for (int i=0;i<threadCount;i++){
            tp.submit(new appendThread(content,writeCount,outputStream));
        }
        tp.shutdown();
    }
    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class appendThread implements Callable<Integer>{
        private String content;
        private int count;
        private FileOutputStream outputStream;
        @Override
        public Integer call () throws Exception {
            System.out.println(Thread.currentThread().getName());
            content+=Thread.currentThread().getName();
            for (int i=0;i<count;i++){
                outputStream.write(content.getBytes());
            }
            System.out.println(Thread.currentThread().getName()+"write finished!");
            return content.length();
        }
    }
}
