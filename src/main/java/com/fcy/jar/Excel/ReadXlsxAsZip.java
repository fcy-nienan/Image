package com.fcy.jar.Excel;

import com.fcy.Java.XML.SAX;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ReadXlsxAsZip {
    public static void main(String args[]) throws Exception {
        List<String> result=getHeadValueList("F:\\1.xlsx","sheet1.xml",2,1);
        result.forEach(e->{
            System.out.println(e);
        });

//        ExcelSalParser salParser=new ExcelSalParser(3,1);
//        try {
//            getSaxParser().parse(new FileInputStream("F:\\sheet111.xml")
//                    , salParser);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        salParser.getResult().forEach(e->{
//            System.out.println(e);
//        });
    }
    private static SAXParser getSaxParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory parser=SAXParserFactory.newInstance();
        SAXParser saxParser=parser.newSAXParser();
        return saxParser;
    }
    public static List<String> getHeadValueList(String path,String endZipName,int columnNumber,int rowNumber) throws IOException, ParserConfigurationException, SAXException {
        long start,end;
        start=System.currentTimeMillis();
        InputStream inputStream=getZipInputStream(path,endZipName);
        if (inputStream==null){
            throw new NullPointerException("unable to find the "+endZipName+" resources at "+path);
        }

        ExcelSalParser excelSalParser=new ExcelSalParser(columnNumber,rowNumber);
        try {
            getSaxParser().parse(inputStream, excelSalParser);
        }catch (Exception e){//SAX得到需要的数据后手动抛出异常
            System.out.println("已找到需要的数据,终止运行!");
        }

        end=System.currentTimeMillis();
        List<String> result=excelSalParser.getResult();
        System.out.println("cost time:"+(end-start));
        return result;
    }
    private static InputStream getZipInputStream(String path,String endEntryName) throws IOException {
        if (endEntryName==null){
            throw new NullPointerException("endEntryName can not be null!");
        }
        ZipFile zf=new ZipFile(path);
        ZipInputStream inputStream=new ZipInputStream(new FileInputStream(path));
        ZipEntry zipEntry=null;
        InputStream stream=null;
        while ((zipEntry=inputStream.getNextEntry())!=null){
            if (zipEntry.isDirectory())
                continue;
            String name=zipEntry.getName();
            if (name.endsWith(endEntryName)){
                System.out.println("find entry :"+name);
                stream=zf.getInputStream(zipEntry);
                break;
            }
        }
        return stream;
    }
    static class ExcelSalParser extends DefaultHandler{
        private int minColumn;
        private String rowNumber;
        private List<String> result=new ArrayList();
        private boolean needStop;//得到需要的数据后终止
        private boolean needGetData;//是否碰到了需要的标签
        private boolean metRow;//是否碰到了指定的行
        public List<String> getResult(){
            return result;
        }
        public ExcelSalParser(int minColumn,int rowNumber){
            if (minColumn<=0){
                throw new IllegalArgumentException("minColumn can not be less than zero!");
            }
            if (rowNumber<=0){
                throw new IllegalArgumentException("rowNumber can not be less than zero!");
            }
            this.minColumn=minColumn;
            this.rowNumber=String.valueOf(rowNumber);
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            checkNeedStop();
//            如果到了指定的行
            System.out.println("start:"+qName);
            if (qName.equals("row")&&attributes.getValue("r").equals(rowNumber)){
                metRow=true;
            }
            if (metRow) {
                if (qName.equals("v")){
                    needGetData=true;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            checkNeedStop();
            System.out.println("end:"+qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            checkNeedStop();
            if (minColumn>0){
                if (needGetData) {
                    System.out.println("data:"+new String(ch,start,length));
                    result.add(new String(ch, start, length));
                    minColumn--;
                    needGetData=false;
                }
            }else{
                needStop=true;
            }
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            //        no op
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            //        no op
        }

        @Override
        public void startDocument() throws SAXException {
            //        no op
        }

        @Override
        public void endDocument() throws SAXException {
            //        no op
        }
        private void checkNeedStop() throws SAXException {
            if (needStop){
                throw new SAXException("已找到需要的数据,终止运行!");
            }
        }
    }
}
