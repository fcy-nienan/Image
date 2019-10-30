package Diff;

import CommonUtil.IOUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipDiff {
    public static void main(String args[]) throws IOException {
        String src="D:\\startup.zip";
        String dst="D:\\startup1.zip";
        List<FileDifferentInfo> contentDifferentInfos = diffZip(src, dst);
        for (FileDifferentInfo contentDifferentInfo : contentDifferentInfos) {
            System.out.println(contentDifferentInfo);
        }
    }
    public static List<FileDifferentInfo> diffZip(String src, String dst) throws IOException {
        List<FileDifferentInfo> fileDifferentInfos =new ArrayList<>();
        ZipFile srcZip=new ZipFile(src);
        Enumeration<? extends ZipEntry> srcEntries = srcZip.entries();
        ZipFile dstZip=new ZipFile(dst);
        Enumeration<? extends ZipEntry> dstEntries = dstZip.entries();
        while (true){
            ZipEntry srcEntry=null,dstEntry=null;
            if (srcEntries.hasMoreElements()) {
                srcEntry = srcEntries.nextElement();
            }
            if (dstEntries.hasMoreElements()){
                dstEntry = dstEntries.nextElement();
            }
            if (srcEntry==null&&dstEntry==null){
                break;
            }
//            比较两个ZipEntry的不同
        }
        return fileDifferentInfos;
    }
    public static List<ContentDifferentInfo> getZipEntryDifferent(ZipEntry src, ZipEntry dst) throws IOException {
        List<ContentDifferentInfo> infos=new ArrayList<>();
        if (src!=null&&dst!=null) {
            BufferedReader srcReader = IOUtil.getBufferedReaderByByteArray(src.getExtra());
            BufferedReader dstReader = IOUtil.getBufferedReaderByByteArray(dst.getExtra());
            infos=FileDiff.getDifferentReader(srcReader,dstReader);
        }
        return infos;
    }
}
