package File;

import CommonUtil.IOUtil;
import CommonUtil.SystemUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class.getName());
    private static final String charset= SystemUtil.getFileEncoding();
    public static void writeToFile(String path,String msg) throws IOException {
        BufferedWriter writer= IOUtil.getBufferedWriterByString(path);
        writer.write(msg);
        writer.flush();
        writer.close();
    }
    public static void writeToFile(String path, List<String> list)throws IOException{
        BufferedWriter writer=IOUtil.getBufferedWriterByString(path);
        for (String s : list) {
            writer.write(s);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
    public static void appendToFile(String path,byte[] bytes) throws IOException {
        RandomAccessFile file=new RandomAccessFile(path,"rw");
        file.seek(file.length());
        file.write(bytes);
//        file.getFD().sync();
        file.close();
    }
    public static void appendToFile(String path,String content) throws IOException {
        appendToFile(path,content.getBytes(charset));
    }
    public static void cleanFile(String path) throws IOException {
        BufferedWriter bufferedWriterByString = IOUtil.getBufferedWriterByString(path);
        bufferedWriterByString.write("");
        bufferedWriterByString.flush();
        bufferedWriterByString.close();
    }
}
