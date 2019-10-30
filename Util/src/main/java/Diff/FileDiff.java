package Diff;

import CommonUtil.IOUtil;
import CommonUtil.MD5Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static Diff.DiffType.*;

public class FileDiff {
    private static Logger logger = Logger.getLogger(FileDiff.class.getName());

    public static void main(String args[]) throws Exception {
        String src="E:\\a.txt";
        String dst="E:\\b.txt";
        System.out.println(hasDifferent(src,dst));
        List<ContentDifferentInfo> infos=getDifferent(src,dst);
        for (ContentDifferentInfo info : infos) {
            System.out.println(info);
        }
    }

    public static ContentDifferentInfo getByteDifferent(int line, byte[] srcByte, byte[] dstByte) throws UnsupportedEncodingException {
        return getStringDifferent(line,new String(srcByte,"utf-8"),new String(dstByte,"utf-8"));
    }

    public static ContentDifferentInfo getStringDifferent(int line, String srcLine, String dstLine){
        ContentDifferentInfo info=null;
        if (srcLine!=null&&dstLine!=null&&!srcLine.equals(dstLine)){
            info=new ContentDifferentInfo(line,srcLine,dstLine,CONTENTDIFF);
        }else if (srcLine==null&&dstLine!=null){
            info=new ContentDifferentInfo(line,srcLine,dstLine, DELETE);
        }else if (dstLine==null&&srcLine!=null){
            info=new ContentDifferentInfo(line,srcLine,dstLine, ADD);
        }
        return info;
    }
    public static List<ContentDifferentInfo> getDifferentReader(BufferedReader srcReader, BufferedReader dstReader) throws IOException {
        List<ContentDifferentInfo> result=new ArrayList<>();
        int line=1;
        for (;;) {
            String srcLine=srcReader.readLine();
            String dstLine=dstReader.readLine();
            ContentDifferentInfo info=getStringDifferent(line,srcLine,dstLine);
            if (info!=null) {
                result.add(info);
            }

            if (srcLine==null&&dstLine==null){
                break;
            }
            line++;
        }
        return result;
    }
    public static List<ContentDifferentInfo> getDifferent(String src, String dst) throws IOException {
        List<ContentDifferentInfo> result=new ArrayList<>();

        if (hasDifferent(src,dst)){
            BufferedReader srcReader=IOUtil.getBufferedReader(src);
            BufferedReader dstReader=IOUtil.getBufferedReader(dst);
            result=getDifferentReader(srcReader,dstReader);
        }

        return result;
    }

    public static boolean hasDifferent(String src,String dst) throws IOException {
        File srcFile=new File(src);
        File dstFile=new File(dst);

        if (srcFile.length()!=dstFile.length()) {
            logger.warning("length is different!");
            return true;
        }

        String srcString= IOUtil.readStringFromStream(new FileInputStream(srcFile));
        String dstString=IOUtil.readStringFromStream(new FileInputStream(dstFile));
        String srcMd5= MD5Utils.md5(srcString);
        String dstMd5= MD5Utils.md5(dstString);

        if (!srcMd5.equals(dstMd5)){
            logger.warning("content is different!");
            return true;
        }

        return false;
    }
}
