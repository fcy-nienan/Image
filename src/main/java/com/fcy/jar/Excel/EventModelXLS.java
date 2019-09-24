package com.fcy.jar.Excel;

import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.record.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class EventModelXLS implements HSSFListener {
    private SSTRecord sstrec;
    private FormatTrackingHSSFListener listener;
    private int currentColumn;
    private int maxColumn;
    private boolean needStop;
    private List<String> result;
    private boolean skipBlank;
    private int lastColumn=-1;
    private int lastRow=-1;
    public EventModelXLS(int minColumn){
        this(minColumn,false);
    }
    public EventModelXLS(int maxColumn,boolean skipBlank){
        if (maxColumn<=0){
            throw new IllegalArgumentException("maxColumn can not be less then zero!");
        }
        this.maxColumn=maxColumn;
        this.skipBlank=skipBlank;
        MissingRecordAwareHSSFListener ll=new MissingRecordAwareHSSFListener(this);
        listener=new FormatTrackingHSSFListener(ll);
        result=new ArrayList<>();
    }
    public void processRecord(Record record) {
        if (needStop){
            return;
        }
        String cellValue="";
        int thisRow=-1,thisColumn=-1;
        switch (record.getSid()) {
            case BOFRecord.sid:
//                beginning
                break;
            case BoundSheetRecord.sid:
//                met a new sheet
                break;
            case RowRecord.sid:
//                met a new row
                break;
            case NumberRecord.sid:
//                number cell value
                NumberRecord numrec = (NumberRecord) record;
                thisRow=numrec.getRow();
                thisColumn=numrec.getColumn();
                cellValue=getNumbericValue(numrec.getValue());
                break;
            case SSTRecord.sid:
//                can not be deleted ! otherwise there will cause a NullPointerException(may be lazy loading)
                sstrec = (SSTRecord) record;
                break;
            case LabelSSTRecord.sid:
//                text cell value
                LabelSSTRecord lrec = (LabelSSTRecord) record;
                thisRow=lrec.getRow();
                thisColumn=lrec.getColumn();
                cellValue=sstrec.getString(lrec.getSSTIndex()).toString();
                break;
            default:
//                System.out.println("temporarily not processed!");
                break;
        }
        if (thisColumn!=-1&&thisRow!=-1) {
            result.add(cellValue);
            if (skipBlank) {
                currentColumn++;
            }else{
                if (thisRow!=lastRow){
                    lastColumn=-1;
                    lastRow=thisRow;
                }
                int increment=thisColumn-lastColumn;
                currentColumn+=increment;
                lastColumn=thisColumn;
            }
            if (currentColumn>=maxColumn){
                needStop=true;
            }
        }
    }
    public static List<String> getFirstRow(String path,int maxColumn) throws IOException {
        return getFirstRow(path,maxColumn,false);
    }
    public static List<String> getFirstRow(String path,int maxColumn,boolean skipBlank) throws IOException {
        FileInputStream fin = new FileInputStream(path);
        POIFSFileSystem poifs = new POIFSFileSystem(fin);
        InputStream din = poifs.createDocumentInputStream("Workbook");
        HSSFRequest req = new HSSFRequest();
        EventModelXLS modelXLS=new EventModelXLS(maxColumn,skipBlank);
        req.addListenerForAllRecords(modelXLS);
        HSSFEventFactory factory = new HSSFEventFactory();
        factory.processEvents(req, din);
        fin.close();
        din.close();
        return modelXLS.result;
    }
    private static String getNumbericValue(Double dVal){
        Date oDate = new Date();
        long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        oDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));
        int year= oDate.getYear()+1900;
        if (year>1900&&year<3000) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String value=format.format(oDate);
            return value;
        }else{
            String s=String.valueOf(dVal);
            String dotValue=s.substring(s.lastIndexOf(".")+1);
            try {
                if (Integer.parseInt(dotValue) == 0) {
                    return String.valueOf(dVal.intValue());
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
                //ignore
            }
            return String.valueOf(dVal);
        }
    }
}