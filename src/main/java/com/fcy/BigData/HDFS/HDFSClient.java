package com.fcy.BigData.HDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;


/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-22  16:30
 */
public class HDFSClient {
    private Logger logger=Logger.getLogger(HDFSClient.class.getName());
    private String defaultFs="hdfs://localhost:9001";
    private Configuration configuration;
    private FileSystem fileSystem;
    public void getDisHost() throws IOException {
        DatanodeInfo[] datanodeInfo=((DistributedFileSystem)fileSystem).getDataNodeStats();
        for (DatanodeInfo info : datanodeInfo) {
            System.out.println(info.getHostName());
        }

    }
    private void init() throws IOException {
        configuration=new Configuration();
        configuration.set("fs.defaultFS",defaultFs);
        fileSystem=FileSystem.get(configuration);
        RemoteIterator<LocatedFileStatus> iterator=fileSystem.listFiles(new Path("/"),true);
        while (iterator.hasNext()){
            LocatedFileStatus fileStatus=iterator.next();
            disFileStatus(fileStatus);
        }
    }
    public boolean upload(String src,String newName){
        boolean flag=false;
        InputStream inputStream=null;
        try{
            inputStream=new FileInputStream(src);
            FSDataOutputStream outputStream=fileSystem.create(new Path(newName));
            IOUtils.copyBytes(inputStream,outputStream,4096,true);
            flag=true;
        } catch (FileNotFoundException e) {
            logger.info("本地文件丢失!"+e.getMessage());
        } catch (IOException e) {
            logger.info("写入HDFS错误!"+e.getMessage());
        }
        return flag;
    }
    private void checkPath(String path){
        if (path==null||path.equals(""))
            throw new NullPointerException("路径不能为空!");
    }
    private void checkisExist(String path,boolean isCreate) throws FileNotFoundException {
        File file=new File(path);
        if (!file.exists()&&isCreate) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileNotFoundException("创建文件失败!"+e.getMessage());
            }
        }
    }
    public boolean down(String path,String newPath) throws FileNotFoundException {
        if (path==null||newPath==null||path.equals("")||newPath.equals(""))
            return false;
        try {
            FSDataInputStream inputStream = fileSystem.open(new Path(path));
            OutputStream outputStream = new FileOutputStream(newPath);
            IOUtils.copyBytes(inputStream,outputStream,4096,true);
            return true;
        } catch (FileNotFoundException e) {
            logger.info("打开本地文件错误:"+e.getMessage());
        } catch (IOException e) {
            logger.info("打开HDFS文件错误:"+e.getMessage());
        }
        return false;
    }
    public boolean delete(String path){
        if (path==null||path.equals(""))
            return false;
        try{
            fileSystem.delete(new Path(path),true);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        HDFSClient client=new HDFSClient();
        client.init();
        System.out.println(client.upload("G:\\test1", "/test1.txt"));
        System.out.println(client.down("/test1.txt", "D:\\test1.txt"));
        client.getDisHost();
    }
    public void disFileStatus(LocatedFileStatus fileStatus) throws IOException {
        System.out.println("fileStatus.getPath().getName() = " + fileStatus.getPath().getName());
        System.out.println("fileStatus.getOwner() = " + fileStatus.getOwner());
        System.out.println("fileStatus.getAccessTime() = " + fileStatus.getAccessTime());
        System.out.println("fileStatus.getGroup() = " + fileStatus.getGroup());
        System.out.println("fileStatus.getLen() = " + fileStatus.getLen());
        System.out.println("fileStatus.getPermission() = " + fileStatus.getPermission());
        System.out.println("fileStatus.getReplication() = " + fileStatus.getReplication());
        System.out.println("fileStatus.getModificationTime() = " + fileStatus.getModificationTime());
        System.out.println("fileStatus.getBlockSize() = " + fileStatus.getBlockSize());
        BlockLocation[] location=fileStatus.getBlockLocations();
        for (BlockLocation blockLocation : location) {
            for (String name : blockLocation.getNames()) {
                System.out.println("name = " + name);
            }
            for (String host : blockLocation.getHosts()) {
                System.out.println("host = " + host);
            }
            System.out.println("blockLocation.getLength() = " + blockLocation.getLength());
            System.out.println("blockLocation.getOffset() = " + blockLocation.getOffset());
        }
        System.out.println("------------------------------------------------");
    }
}
