package Diff;

import CommonUtil.IOUtil;

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
        List<ContentDifferentInfo> contentDifferentInfos = diffZip(src, dst);
        for (ContentDifferentInfo contentDifferentInfo : contentDifferentInfos) {
            System.out.println(contentDifferentInfo);
        }
    }
    public static List<FileDifferentInfo> diffZip(String src, String dst, boolean detail) throws IOException {
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
            List<ContentDifferentInfo> zipEntryDifferent = getZipEntryDifferent(srcEntry, dstEntry);
            if (zipEntryDifferent!=null&&zipEntryDifferent.size()>=0){
                FileDifferentInfo fileDifferentInfo=new FileDifferentInfo(srcEntry.getName(), dstEntry.getName(),zipEntryDifferent);
                fileDifferentInfos.add(fileDifferentInfo);
            }
            if (srcEntry==null&&dstEntry==null){
                break;
            }
        }
        return fileDifferentInfos;
    }
    public static List<ContentDifferentInfo> getZipEntryDifferent(ZipEntry src, ZipEntry dst) throws IOException {
        BufferedReader srcReader=IOUtil.getBufferedReaderByByteArray(src.getExtra());
        BufferedReader dstReader=IOUtil.getBufferedReaderByByteArray(dst.getExtra());
        return FileDiff.getDifferentReader(srcReader,dstReader);
    }
}
