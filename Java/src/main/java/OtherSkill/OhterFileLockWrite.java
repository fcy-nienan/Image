package OtherSkill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OhterFileLockWrite {
    public static void main (String args[]) throws IOException {
        String home=System.getProperty("user.home");
        String path=home+ File.separator+"command";
        File file=new File(path);
        FileOutputStream outputStream=new FileOutputStream(file);
        for (int i=0;i<10;i++){
            outputStream.write(i+65);
        }
        outputStream.close();
    }
}
