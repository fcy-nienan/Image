package sort;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class outerSortHome {
    private static final String DEFAULT_FILENAME="D:\\data";
    private static final long NUMBER_COUNT=12;
    private static final int DATA_DISTANCE=Integer.MAX_VALUE;
    private static final int FLUSH_THRESHOLD=100000;
    public static void main(String args[]) throws IOException {
        preparedFile();
    }
//    生成一个类似于1,2,8,3这样的数据文件
    private static void preparedFile() throws IOException {
        Random random=new Random();
        FileOutputStream outputStream=new FileOutputStream(DEFAULT_FILENAME);
        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
        for(int i=1;i<=NUMBER_COUNT;i++){
            int x=random.nextInt(DATA_DISTANCE);
            byte[] bytes=i2b(i);
//            outputStream.write(bytes);
            dataOutputStream.writeInt(x);
            dataOutputStream.write(x);
            if (i%FLUSH_THRESHOLD==0){
                outputStream.flush();
                outputStream.getFD().sync();
            }
        }
        outputStream.flush();
        outputStream.close();
    }
    private static byte[] i2b(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)((i>>24)&0xff);
        bytes[1]=(byte)((i>>16)&0xff);
        bytes[2]=(byte)((i>>8)&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
    private static int b2i(byte[] bytes){
        int i=(bytes[0]&0xff)<<24;
        i+=(bytes[1]&0xff)<<24;
        i+=(bytes[2]&0xff)<<8;
        i+=(bytes[3]&0xff);
        return i;
    }
}
