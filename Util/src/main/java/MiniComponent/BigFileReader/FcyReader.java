package MiniComponent.BigFileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FcyReader {
    private String fileName;
    private FileChannel channel;
    private MappedByteBuffer mappedByteBuffer;
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
    public byte[] getResult(int startIndex,int endIndex) throws IOException {
        mappedByteBuffer=channel.map(FileChannel.MapMode.READ_ONLY,startIndex,endIndex);
        byte[] bytes=new byte[endIndex-startIndex];
        mappedByteBuffer.get(bytes);
        return bytes;
    }

    public static void main (String[] args) throws IOException {
        FcyReader reader=new FcyReader("D:\\data.txt");
        byte[] result = reader.getResult(0, 100);
        System.out.println(new String(result,0,result.length));
    }
}
