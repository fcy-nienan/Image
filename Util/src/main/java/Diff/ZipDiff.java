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
            BufferedReader srcReader=null,dstReader=null;
            String srcName=null,dstName=null;
            DiffType diffType=null;
            if (srcEntry==null){
                srcReader=IOUtil.getEmptyReader();
                srcName="*****";
                diffType=DiffType.ADD;
            }else{
                srcReader=IOUtil.getBufferedReaderByByteArray(srcEntry.getExtra());
                srcName=srcEntry.getName();
            }
            if (dstEntry==null){
                dstReader=IOUtil.getEmptyReader();
                dstName="*****";
                diffType=DiffType.DELETE;
            }else{
                dstReader=IOUtil.getBufferedReaderByByteArray(dstEntry.getExtra());
                dstName=dstEntry.getName();
            }
            List<ContentDifferentInfo> differentReader = FileDiff.getDifferentReader(srcReader, dstReader);
            if (differentReader!=null&&differentReader.size()>0){
                diffType=DiffType.CONTENTDIFF;
            }
            if (diffType!=null) {
                FileDifferentInfo info = new FileDifferentInfo(srcName, dstName, diffType,differentReader);
                fileDifferentInfos.add(info);
            }
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
