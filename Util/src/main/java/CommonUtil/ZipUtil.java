package CommonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static Logger logger=Logger.getLogger(ZipUtil.class.getName());
    private static ZipOutputStream outputStream;
    public static void compress(String src,String dest){
        try {
            checkPath(src,dest);
            write(src,dest);
            outputStream.close();
        }catch (FileNotFoundException file){
            logger.log(Level.WARNING,"目标文件不存在!");
        }catch (IOException io){
            logger.log(Level.WARNING,"压缩错误!");
        }
    }
    private static void checkPath(String src,String dest) throws FileNotFoundException {
        if (src==null){
            throw new NullPointerException("路径不能为空!");
        }
        if (dest==null){
            String baseDir=System.getProperty("user.dir");
            dest=baseDir+File.separator+UUID.randomUUID().toString()+ ".zip";
            logger.log(Level.WARNING,"目标文件不能为空!已随机生成文件名:"+dest);
        }
        if (outputStream==null)
            outputStream=new ZipOutputStream(new FileOutputStream(dest));
    }
    private static void write(String src,String dest) throws IOException {
        File file=new File(src);
        if (file.isDirectory()){
            File[] files=file.listFiles();
            for (File file1 : files) {
                String path=file1.getAbsolutePath();
                write(path,dest);
            }
        }else {
            ZipEntry entry = new ZipEntry(src);
            outputStream.putNextEntry(entry);
            byte[] bytes = Files.readAllBytes(Paths.get(src));
            outputStream.write(bytes);
            outputStream.closeEntry();
        }
    }
}
