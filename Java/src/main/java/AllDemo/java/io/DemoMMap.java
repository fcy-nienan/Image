package AllDemo.java.io;

import com.google.common.base.Stopwatch;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.logging.Logger;

public class DemoMMap {
    private static Logger logger = Logger.getLogger(DemoMMap.class.getName());

    public static void main(String args[]) throws Exception {
        Stopwatch sw=new Stopwatch();
        File file=new File("D:\\command");
<<<<<<< HEAD
        file.createNewFile();
=======
>>>>>>> tmp
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        sw.start();
        MappedByteBuffer map = channel.map(MapMode.READ_WRITE, 0, 1000);
        byte[] bytes=new byte[1000];
        ByteBuffer buffer = map.get(bytes);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        sw.stop();
        System.out.println(sw.toString());

    }
}
