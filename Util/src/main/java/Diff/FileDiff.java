package Diff;

import CommonUtil.IOUtil;
import CommonUtil.MD5Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static Diff.DiffInfo.DiffType.*;

public class FileDiff {
    private static Logger logger = Logger.getLogger(FileDiff.class.getName());

    public static void main(String args[]) throws Exception {
        String src="E:\\a.txt";
        String dst="E:\\b.txt";
        System.out.println(hasDifferent(src,dst));
        List<DiffInfo> infos=getDifferent(src,dst);
        for (DiffInfo info : infos) {
            System.out.println(info);
        }
    }

    public static List<DiffInfo> getDifferent(String src,String dst) throws IOException {
        List<DiffInfo> result=new ArrayList<>();

        if (hasDifferent(src,dst)){
            BufferedReader srcreader=IOUtil.getBufferedReader(src);
            BufferedReader dstReader=IOUtil.getBufferedReader(dst);
            int line=1;
            for (;;) {
                String srcLine=srcreader.readLine();
                String dstLine=dstReader.readLine();
                DiffInfo info=null;
                if (srcLine!=null&&dstLine!=null&&!srcLine.equals(dstLine)){
                    info=new DiffInfo(line,srcLine,dstLine,CONTENTDIFF);
                }else if (srcLine==null&&dst!=null){
                    info=new DiffInfo(line,srcLine,dstLine, DELETE);
                }else if (dstLine==null&&srcLine!=null){
                    info=new DiffInfo(line,srcLine,dstLine, ADD);
                }

                if (info!=null) {
                    result.add(info);
                }

                if (srcLine==null&&dstLine==null){
                    break;
                }
                line++;
            }
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
