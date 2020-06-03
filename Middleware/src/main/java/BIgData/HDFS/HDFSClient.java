package BIgData.HDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSClient {
    public static void main (String args[]) throws IOException {
        createFile("/user/input/file1");
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
////        // 打开一个输出流
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write(1);
        outputStream.write("java js javascript c c++ \r\nscala php go python".getBytes());
        outputStream.close();
        fs.close();
        System.out.println("文件创建成功！");
    }
}
