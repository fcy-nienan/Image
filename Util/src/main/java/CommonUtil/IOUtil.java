package CommonUtil;

import java.io.*;

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
    public static BufferedReader getBufferedReader(String src) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(src),"utf-8"));
    }
    public static BufferedReader getBufferedReaderByByteArray(byte[] array) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(array),"utf-8"));
    }
    public static BufferedReader getEmptyReader() throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream("".getBytes()),"utf-8"));
    }
    public static String readStringFromStream(InputStream inputStream) throws IOException {
        byte[] bytes=readByteFromStream(inputStream);
        return new String(bytes,"utf-8");
    }
    public static String readStringFromSrc(String src) throws IOException {
        byte[] bytes=readByteFromStream(new FileInputStream(src));
        return new String(bytes,"utf-8");
    }

}
