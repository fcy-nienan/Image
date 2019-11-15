package DynamicLoader;

import com.sun.nio.zipfs.ZipInfo;
import sun.nio.cs.ext.Big5;

import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.logging.Logger;

public class DynamicLoaderMain {
    public static void main(String args[]) throws Exception {
        String home="D:\\classes";
        ClassLoaderTask task=new ClassLoaderTask(home);
        task.start();


    }
}
