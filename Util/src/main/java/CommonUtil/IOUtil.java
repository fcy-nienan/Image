package CommonUtil;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-05  23:34
 */
@Slf4j
public class IOUtil{
    private static String charset=SystemUtil.getFileEncoding();
    private static final String lineSeperator=System.getProperty("line.separator");
    public static void appendToFile(String path,byte[] content) throws IOException {
        File file= new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.write(content);
        randomAccessFile.close();
    }
    public static void disPlayStream(InputStream inputStream,String charset) throws IOException {
        BufferedReader reader=BufferedReader(inputStream);
        String msg=null;
        while ((msg=reader.readLine())!=null){
            System.out.println(msg);
        }
        closeStream(reader);
    }
    public static void disPlayStream(InputStream inputStream) throws IOException {
        disPlayStream(inputStream,charset);
    }
    public static void closeStream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                log.warn("关闭流异常!");
                closeable=null;
            }
        }
    }
    public static void closeStream(Closeable... closeables){
        if (closeables!=null) {
            for (Closeable closeable : closeables) {
                closeStream(closeable);
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

    public static FileInputStream FileInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
    public static FileInputStream FileInputStream(String inpath) throws FileNotFoundException {
        return FileInputStream(new File(inpath));
    }
    public static FileOutputStream FileOutputStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }
    public static FileOutputStream FileOutputStream(String outPath) throws FileNotFoundException {
        return FileOutputStream(new File(outPath));
    }



    public static BufferedReader BufferedReader(InputStream stream,String charset) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(stream,charset));
    }
    public static BufferedReader BufferedReader(InputStream stream,String charset,int size) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(stream,charset),size);
    }
    public static BufferedReader BufferedReader(InputStream stream) throws UnsupportedEncodingException {
        return BufferedReader(stream,SystemUtil.getFileEncoding());
    }
    public static BufferedReader BufferedReader(InputStream stream,int size) throws UnsupportedEncodingException {
        return BufferedReader(stream,SystemUtil.getFileEncoding(),size);
    }
    public static BufferedReader BufferedReader(File file) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new FileInputStream(file));
    }
    public static BufferedReader BufferedReader(File file,int size) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new FileInputStream(file),size);
    }
    public static BufferedReader BufferedReader(File file,String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new FileInputStream(file),charset);
    }
    public static BufferedReader BufferedReader(File file,String charset,int size) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new FileInputStream(file),charset,size);
    }

    public static BufferedReader BufferedReader(String src) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new File(src));
    }
    public static BufferedReader BufferedReader(String src,int size) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new File(src),size);
    }
    public static BufferedReader BufferedReader(String src,String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedReader(new File(src),charset);
    }

    public static BufferedReader BufferedReader(byte[] array) throws UnsupportedEncodingException {
        return BufferedReader(new ByteArrayInputStream(array));
    }

    public static BufferedReader BufferedReader() throws UnsupportedEncodingException {
        return BufferedReader(new ByteArrayInputStream("".getBytes()));
    }




    public static BufferedWriter BufferedWriter(OutputStream outputStream,String charset) throws UnsupportedEncodingException {
        return new BufferedWriter(new OutputStreamWriter(outputStream,charset));
    }
    public static BufferedWriter BufferedWriter(OutputStream outputStream) throws UnsupportedEncodingException {
        return BufferedWriter(outputStream, charset);
    }
    public static BufferedWriter BufferedWriter(File file) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedWriter(new FileOutputStream(file));
    }
    public static BufferedWriter BufferedWriter(File file,String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedWriter(new FileOutputStream(file),charset);
    }
    public static BufferedWriter BufferedWriter(String path,String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedWriter(new File(path),charset);
    }
    public static BufferedWriter BufferedWriter(String path) throws FileNotFoundException, UnsupportedEncodingException {
        return BufferedWriter(new File(path),charset);
    }





    public static String readStringFromStream(InputStream inputStream) throws IOException {
        return new String(readByteFromStream(inputStream),charset);
    }
    public static String readStringFromSrc(String src) throws IOException {
        return new String(readByteFromStream(new FileInputStream(src)),charset);
    }
    public static InputStream getStreamByString(String inpath) throws FileNotFoundException {
        return new FileInputStream(inpath);
    }
    public static String getLineSeparator(){
        return lineSeperator;
    }

    public static void copy(InputStream inputStream,OutputStream outputStream) throws IOException {
        byte[] bytes=new byte[2048];
        int c=0;
        while ((c=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,c);
        }
    }

}
