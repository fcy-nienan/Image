package CommonUtil;

import java.io.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-05  23:34
 */
public class IOUtil{
    private static final String lineSeperator=System.getProperty("line.separator");
    public static void disPlayStream(InputStream inputStream,String charset) throws IOException {
        BufferedReader reader=getBufferedReaderByStream(inputStream);
        String msg=null;
        while ((msg=reader.readLine())!=null){
            System.out.println(msg);
        }
        closeStream(reader);
    }
    public static void disPlayStream(InputStream inputStream) throws IOException {
        disPlayStream(inputStream,"utf-8");
    }
    public static void closeStream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                closeable=null;
            }
        }
    }
    public static byte[] readByteFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        int c;
        byte[] bytes=new byte[1024];
        while ((c=inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,c);
        }
        return byteArrayOutputStream.toByteArray();
    }
    public static BufferedReader getBufferedReaderByStream(InputStream stream,String charset) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(stream,charset));
    }
    public static BufferedReader getBufferedReaderByStream(InputStream stream) throws UnsupportedEncodingException {
        return getBufferedReaderByStream(stream,"utf-8");
    }
    public static BufferedReader getBufferedReader(String src) throws FileNotFoundException, UnsupportedEncodingException {
        return getBufferedReaderByStream(new FileInputStream(src));
    }
    public static BufferedReader getBufferedReaderByByteArray(byte[] array) throws UnsupportedEncodingException {
        return getBufferedReaderByStream(new ByteArrayInputStream(array));
    }
    public static BufferedReader getEmptyReader() throws UnsupportedEncodingException {
        return getBufferedReaderByStream(new ByteArrayInputStream("".getBytes()));
    }
    public static String readStringFromStream(InputStream inputStream) throws IOException {
        return new String(readByteFromStream(inputStream),"utf-8");
    }
    public static String readStringFromSrc(String src) throws IOException {
        return new String(readByteFromStream(new FileInputStream(src)),"utf-8");
    }
    public static String getLineSeparator(){
        return lineSeperator;
    }

}
