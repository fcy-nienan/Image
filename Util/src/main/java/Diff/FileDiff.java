package Diff;

import Common.ContentDifferentInfo;
import CommonUtil.IOUtil;
import CommonUtil.MD5Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static Common.DiffType.*;

public class FileDiff {
    private static Logger logger = Logger.getLogger(FileDiff.class.getName());

    public static void main(String args[]) throws Exception {

    }

    private static ContentDifferentInfo getByteDifferent(int line, byte[] srcByte, byte[] dstByte) throws UnsupportedEncodingException {
        return getStringDifferent(line,new String(srcByte,"utf-8"),new String(dstByte,"utf-8"));
    }

    private static ContentDifferentInfo getStringDifferent(int line, String srcLine, String dstLine){
        ContentDifferentInfo info=null;
        if (srcLine!=null&&dstLine!=null&&!srcLine.equals(dstLine)){
            info=new ContentDifferentInfo(line,srcLine,dstLine,NOT_EQUAL_CONTENT);
        }else if (srcLine==null&&dstLine!=null){
            info=new ContentDifferentInfo(line,srcLine,dstLine, DELETE_CONTENT);
        }else if (dstLine==null&&srcLine!=null){
            info=new ContentDifferentInfo(line,srcLine,dstLine, ADD_CONTENT);
        }else{
            info=new ContentDifferentInfo(line,srcLine,dstLine,EQUAL_CONTENT);
        }
        return info;
    }
    private static List<ContentDifferentInfo> getDifferentReader(BufferedReader srcReader, BufferedReader dstReader) throws IOException {
        List<ContentDifferentInfo> result=new ArrayList<>();
        int line=1;
        for (;;) {
            String srcLine=srcReader.readLine();
            String dstLine=dstReader.readLine();
            logger.info("srcLine:"+srcLine+"--dstLine:"+dstLine);
            ContentDifferentInfo info=getStringDifferent(line,srcLine,dstLine);
            result.add(info);

            if (srcLine==null&&dstLine==null){
                break;
            }
            line++;
        }
        return result;
    }
    public static List<ContentDifferentInfo> getDifferent(BufferedInputStream src,BufferedInputStream dst) throws IOException {
        List<ContentDifferentInfo> result=new ArrayList<>();

        src.mark(0);
        dst.mark(0);
        if (hasDifferent(src,dst)){
            src.reset();
            dst.reset();
            BufferedReader srcReader=IOUtil.getEmptyReader(),
                    dstReader=IOUtil.getEmptyReader();
            if (src!=null){
                srcReader=IOUtil.getBufferedReaderByStream(src);
            }
            if (dst!=null){
                dstReader=IOUtil.getBufferedReaderByStream(dst);
            }
            logger.info("srcReader:"+srcReader+"--dstReader"+dstReader);
            result=getDifferentReader(srcReader,dstReader);
        }

        return result;
    }
    private static boolean hasDifferent(InputStream src,InputStream dst) throws IOException {
        if (src==null||dst==null){
            return true;
        }
        String srcString= IOUtil.readStringFromStream(src);
        String dstString=IOUtil.readStringFromStream(dst);
        String srcMd5= MD5Utils.md5(srcString);
        String dstMd5= MD5Utils.md5(dstString);

        if (!srcMd5.equals(dstMd5)){
            logger.warning("content is different!");
            return true;
        }

        return false;
    }

    /*
    * 判断两个文件是否相同
    * src源文件路径
    * dst目标文件路径
    * */
    private static boolean hasDifferent(String src,String dst) throws IOException {
        File srcFile=new File(src);
        File dstFile=new File(dst);

        if (srcFile.length()!=dstFile.length()) {
            logger.warning("length is different!");
            return true;
        }
        return hasDifferent(new FileInputStream(srcFile),new FileInputStream(dstFile));
    }
}
