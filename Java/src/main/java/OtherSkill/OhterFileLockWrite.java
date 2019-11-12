package OtherSkill;

import CommonUtil.IOUtil;

import java.io.*;
import java.nio.channels.FileLock;

public class OhterFileLockWrite {
    public static void main (String args[]) throws IOException {
        String home=System.getProperty("user.home");
        String path=home+ File.separator+"command";
        File file=new File(path);
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        randomAccessFile.seek(0);
        for (int i=0;i<10;i++){
            randomAccessFile.write(i+65);
        }
        randomAccessFile.close();

        IOUtil.disPlayStream(new FileInputStream(file));
    }
}
