package BIgData.HDFS;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HDFSClient {
    public static void main (String args[]) throws IOException {
        createFile("txt");
    }
    public static void createFile(String dst) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.198.1:9000");
        FileSystem fs = FileSystem.get(conf);
        long length = fs.getLength(new Path("txt"));
        Path dstPath=new Path(dst);
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(dstPath,0,length);

        System.out.println(fileBlockLocations);
//        // 打开一个输出流
//        FSDataOutputStream outputStream = fs.create(dstPath);
//        outputStream.write(1);
//        outputStream.close();
//        fs.close();
        System.out.println("文件创建成功！");
    }
}
