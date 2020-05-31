package CommonUtil;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class.getName());
    private static final String charset= SystemUtil.getFileEncoding();
    public static void writeToFile(String path,String msg) throws IOException {
        BufferedWriter writer= IOUtil.BufferedWriter(path);
        writer.write(msg);
        writer.flush();
        writer.close();
    }
    public static void writeToFile(String path, List<String> list)throws IOException{
        BufferedWriter writer=IOUtil.BufferedWriter(path);
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
        BufferedWriter bufferedWriterByString = IOUtil.BufferedWriter(path);
        bufferedWriterByString.write("");
        bufferedWriterByString.flush();
        bufferedWriterByString.close();
    }
    public static void cp(File file1,File file2) throws IOException {
        FileInputStream inputStream = IOUtil.FileInputStream(file1);
        FileOutputStream outputStream = IOUtil.FileOutputStream(file2);
        IOUtil.copy(inputStream,outputStream);
        IOUtil.closeStream(inputStream,outputStream);
    }
    public static void cpToDir(String inpath,String outDir) throws IOException {
        File file1=new File(inpath);
        File file2=new File(outDir+file1.getName());
        cp(file1,file2);
    }
    public static void cp(String inpath,String outPath) throws IOException {
        File file1=new File(inpath);
        File file2=new File(outPath);
        cp(file1,file2);
    }
    public static void cp(String inpath,String outDir,String newName) throws IOException {
        File file1=new File(inpath);
        File file2=new File(outDir+newName);
        cp(file1,file2);
    }
    public static void cpDir(String inDir,String outDir) throws IOException {
        File file=new File(inDir);
        File[] files = file.listFiles();
        if (files!=null){
            for (File file1 : files) {
                cpToDir(file1.getAbsolutePath(),outDir);
            }
        }
    }




    public static void delete(String dir){
        delete(new File(dir));
    }
    public static void delete(File file){
        if (file.exists()){
            if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files!=null){
                    for (File file1 : files) {
                        delete(file1);
                    }
                }
            }else{
                if (!file.delete()){
                    logger.warning("删除文件失败:"+file.getAbsolutePath());
                }
            }
        }
    }
}
