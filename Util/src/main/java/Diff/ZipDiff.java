package Diff;

import Common.ContentDifferentInfo;
import Common.DiffType;
import Common.FileDifferentInfo;
import CommonUtil.IOUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipDiff {
    private static Logger logger = Logger.getLogger(FileDiff.class.getName());
    public static void main(String args[]) throws IOException {
        String src="E:\\sample.war";
        String dst="E:\\sample1.war";
        List<FileDifferentInfo> contentDifferentInfos = diffZip(src, dst);
        for (FileDifferentInfo fileDifferentInfo : getFileDifferent(DiffType.NOT_EQUAL_CONTENT, contentDifferentInfos)) {
            System.out.println(fileDifferentInfo);
        }
    }
    public static List<FileDifferentInfo> getFileDifferent(DiffType type,List<FileDifferentInfo> infos){
        for (FileDifferentInfo info : infos) {
            info.setDifferentInfos(getSpecific(type,info.getDifferentInfos()));
        }
        return infos;
    }
    public static List<ContentDifferentInfo> getSpecific(DiffType type,List<ContentDifferentInfo> infos){
        List<ContentDifferentInfo> result=new ArrayList<>();
        for (ContentDifferentInfo info : infos) {
            if (info.getType().equals(type)){
                result.add(info);
            }
        }
        return result;
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
            DiffType fileDiffType=null;
            BufferedInputStream srcStream=null,dstStream=null;
            String srcName="*****",dstName="*****";
            if (srcEntry==null){
                fileDiffType=DiffType.DELETE_FILE;
            }else{
                srcStream=new BufferedInputStream(srcZip.getInputStream(srcEntry));
                srcName= srcEntry.getName();
            }
            if (dstEntry==null){
                fileDiffType=DiffType.ADD_FILE;
            }else{
                dstStream=new BufferedInputStream(dstZip.getInputStream(dstEntry));
                dstName=dstEntry.getName();
            }
            logger.info("srcName:"+srcName+"--dstName:"+dstName);
            logger.info("srcStream:"+srcStream+"--dstStream:"+dstStream);
            if (srcStream!=null&&dstStream!=null){
                List<ContentDifferentInfo> different = FileDiff.getDifferent(srcStream, dstStream);
                if (different.size()>0){
                    fileDiffType=DiffType.NOT_EQUAL_FILE;
                }
                if (fileDiffType!=null) {
                    FileDifferentInfo info = new FileDifferentInfo(srcName, dstName, fileDiffType, different);
                    fileDifferentInfos.add(info);
                }
            }
        }
        return fileDifferentInfos;
    }
}
