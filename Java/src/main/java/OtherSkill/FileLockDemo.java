package OtherSkill;

import java.io.*;
import java.nio.channels.FileLock;

public class FileLockDemo {
    public static void main (String args[]) throws IOException, InterruptedException {
        String home=System.getProperty("user.home");
        String path=home+File.separator+"command";
        File file=new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        FileInputStream inputStream=new FileInputStream(file);
        FileLock lock=inputStream.getChannel().lock(0,10,true);
        Thread.sleep(1000000);
        lock.release();



    }
}
