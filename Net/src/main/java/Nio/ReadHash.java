package Nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-24  11:39
 */
public class ReadHash {
    private static String getFileName(int i){
        return "G:\\tty\\"+i+".hdmd";
    }
    public static void main(String[] args) throws IOException, InterruptedException {
//        useStream();
        mmap();
    }
    public static void useReader()throws Exception{
        HashMap<String,String> map=new HashMap(500000);
        long count=0;
        for(int i=1;i<=8;i++){
            long start=System.currentTimeMillis();
            BufferedReader reader=new BufferedReader(new FileReader(getFileName(i)),1024*5);
            try {
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] s=line.split(",");
                    map.put(s[0],s[1]);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            long end=System.currentTimeMillis();
            System.out.printf("cost time : %s!",(end-start));
            count+=(end-start);
            System.out.println();
        }
        System.out.printf("total cost time : %s !",count);
        System.out.println(map.size());
        Thread.sleep(1000000);
    }
    public static void useStream() throws InterruptedException, FileNotFoundException {
        HashMap<String,String> map=new HashMap(300000);

        map.put("sdf","sdf");
        long count=0;
        long start=0,end=0;
        long cstart=0,cend=0;
        cstart=System.currentTimeMillis();
        for(int i=1;i<=8;i++){
            BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(getFileName(i)),1024*5);
            try {
                byte[] head=new byte[43];
                inputStream.read(head,0,head.length);
                byte[] bytes=new byte[1050];
                int c=0;
                start=System.currentTimeMillis();
                while((c=inputStream.read(bytes,0,bytes.length))!=-1){
//                    String key=new String(bytes,0,23);
//                    String value=new String(bytes,24,1048-24);
                    map.put(new String(bytes,0,23),new String(bytes,24,1048-24));
                }
                end=System.currentTimeMillis();
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("cost time : %s!",(end-start));
            count+=(end-start);
            System.out.println();
        }
        cend=System.currentTimeMillis();
        System.out.println(cend-cstart);
        System.out.printf("total cost time : %s !",count);
        System.out.println(map.size());
        Thread.sleep(1000000);
    }
    public static void mmap() throws IOException {
        HashMap<String,String> map=new HashMap(1000000);
        long start=0,end=0;
        start=System.currentTimeMillis();
        for(int i=1;i<=8;i++){
            File file=new File(getFileName(i));
            long size=file.length();
            FileInputStream inputStream=new FileInputStream(file);
            FileChannel channel=inputStream.getChannel();
            MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY,0,size);
            byte[] head=new byte[43];
            buffer.get(head);
            while(buffer.hasRemaining()){
                byte[] line=new byte[1050];
                buffer.get(line);
//                String key=new String(line,0,24);
//                String value=new String(line,24,1024);
//                map.put(new String(line,0,24),new String(line,24,1024));
            }
//            System.out.println(map.size());
        }
        end=System.currentTimeMillis();
        System.out.printf("cost time %s !",(end-start));
    }
}
