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
        FileOutputStream inputStream=new FileOutputStream(file);
        FileLock lock=inputStream.getChannel().lock();
        Thread.sleep(1000000);
        lock.release();



    }
}
