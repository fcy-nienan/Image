package DynamicLoader;

import com.sun.nio.zipfs.ZipInfo;
import sun.nio.cs.ext.Big5;

import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.logging.Logger;

public class DynamicLoaderMain {
    public static void main(String args[]) throws Exception {

        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println();
        for (String s : System.getProperty("java.class.path").split(";")) {
            System.out.println(s);
        }
        String home="D:\\classes";
        ClassLoaderTask task=new ClassLoaderTask(home);
        System.out.println(String.class.getClassLoader());
        System.out.println(ZipInfo.class.getClassLoader());
        System.out.println(Big5.class.getClassLoader());
        Class clazz=Class.forName("com.fcy.fcyy");
        System.out.println(clazz.getClassLoader());

//        task.start();
    }
}
