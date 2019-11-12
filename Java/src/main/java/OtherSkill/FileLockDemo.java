package OtherSkill;

import java.io.File;
import java.io.FileInputStream;

public class FileLockDemo {
    public static void main (String args[]) {
        String home=System.getProperty("user.home");
        String path=home+File.separator+"command";
        File file=new File(path);
        FileInputStream inputStream=new FileInputStream(file);
        inputStream.getChannel().lock();
        inputStream.getChannel().tryLock();
        inputStream.getChannel().truncate();
    }
}
