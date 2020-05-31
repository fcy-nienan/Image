package MiniComponent.BigFileReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
@Slf4j
public class FcyReader {
    private FileChannel channel;
    private MappedByteBuffer mappedByteBuffer;
    private String path;
    public FcyReader(String fileName){
        File file=new File(fileName);
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        channel = fileInputStream.getChannel();
    }
    public void close(){
        try {
            channel.close();
        } catch (IOException e) {
            log.warn("close channel failure!");
        }
    }
    public byte[] getResult(int startIndex,int endIndex) throws IOException {
        mappedByteBuffer=channel.map(FileChannel.MapMode.READ_ONLY,startIndex,endIndex);
        byte[] bytes=new byte[endIndex-startIndex];
        mappedByteBuffer.get(bytes);
        return bytes;
    }

    public static void main (String[] args) throws IOException {
//        FcyReader reader=new FcyReader("E:\\outerSort\\data");
//        byte[] result = reader.getResult(1111111, 1111711);
//        System.out.println(new String(result,0,result.length));
        File file=new File("E:sf\\sdf");
        System.out.println(file.isFile());
        String[] list = file.list();
        System.out.println(file.getAbsolutePath());
        FileInputStream inputStream=new FileInputStream(file);
    }
}
