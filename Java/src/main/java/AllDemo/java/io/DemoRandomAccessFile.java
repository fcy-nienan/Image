package AllDemo.java.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class DemoRandomAccessFile {
    private static Logger logger = Logger.getLogger(DemoRandomAccessFile.class.getName());

    public static void main(String args[]) throws Exception {
        String path = "E:\\command";
        if (!new File(path).exists()){
            new File(path).createNewFile();
        }
        RandomAccessFile file = new RandomAccessFile(path, "rws");
        String msg="abc";
        file.seek(0);
        file.write(msg.getBytes());
        file.seek(0);
        file.write("lskdjfsdkf".getBytes());
        file.getFD();
        file.getChannel();
        file.getFilePointer();
        file.close();
    }
}
