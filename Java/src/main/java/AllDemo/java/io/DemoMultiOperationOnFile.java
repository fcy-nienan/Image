package AllDemo.java.io;

import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;
/*
* 两个线程使用两个文件描述符会造成写覆盖
* 两个线程使用同一个文件描述符不会写覆盖,但哪个filepointer返回的index是不准确的
*
* */
public class DemoMultiOperationOnFile {
    private static Logger logger = Logger.getLogger(DemoMultiOperationOnFile.class.getName());

    public static void main(String args[]) throws Exception {
        String fileName="D:\\command";
        RandomAccessFile file=new RandomAccessFile(fileName,"rw");
        otherWriter otherWriter=new otherWriter(file);
        otherWriter.start();
        while (true){
            Thread.sleep(30);
            System.out.println("main thread:"+file.getFilePointer());
            file.write(48);                                                                                                                                    
        }
    }
    @AllArgsConstructor
    static class otherWriter extends Thread{
        private RandomAccessFile file;
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(30);
                    System.out.println("other writer:"+file.getFilePointer());
                    file.write(49);
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }
            }

        }
    }
}
