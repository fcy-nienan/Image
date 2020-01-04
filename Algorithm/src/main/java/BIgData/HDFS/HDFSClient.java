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
        fs.mkdirs(new Path("/user/input"));
        fs.mkdirs(new Path("/user/output"));
        FileStatus[] fileStatuses = fs.listStatus(new Path("/user"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath());
        }
        Path dstPath=new Path(dst);
//        // 打开一个输出流
//        FSDataOutputStream outputStream = fs.create(dstPath);
//        outputStream.write(1);
//        outputStream.close();
//        fs.close();
        System.out.println("文件创建成功！");
    }
}
