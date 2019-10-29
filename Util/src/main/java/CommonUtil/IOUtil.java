package CommonUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-05  23:34
 */
public class IOUtil {
    public static byte[] readByteFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        int c;
        byte[] bytes=new byte[1024];
        while ((c=inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,c);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
