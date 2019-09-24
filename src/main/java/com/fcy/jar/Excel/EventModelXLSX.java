package com.fcy.jar.Excel;

import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EventModelXLSX {
    public static List<String> processOneSheet(String filename,int minColumns) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        SheetHandler handler = new SheetHandler(sst,minColumns);
        parser.setContentHandler(handler);

        InputStream sheet2 = r.getSheet("rId1");
        InputSource sheetSource = new InputSource(sheet2);
        try {
            parser.parse(sheetSource);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        sheet2.close();
//        可以不用关,close方法里面是保存该文件的修改,但是目前只是读取文件内容,开了这个方法之后时间会多消耗3s
//        pkg.close();
        return  handler.result;
    }
    private static class SheetHandler extends DefaultHandler {
        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        private int minColumns;
        private boolean needStop=false;
        private List<String> result=new ArrayList<>();
        public List<String> getResult(){
            return this.result;
        }
        private SheetHandler(SharedStringsTable sst,int minColumns) {
            if (minColumns<=0){
                throw new NullPointerException("minColumns can not less than zero!");
            }
            this.minColumns=minColumns;
            this.sst = sst;
        }
        private void checkNeedStop() throws SAXException {
            if (needStop){
                throw new SAXException("已找到需要的数据,通过异常终止SAX解析!");
            }
        }
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            checkNeedStop();
            if (name.equals("c")) {
                String cellType = attributes.getValue("t");
                if (cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            lastContents = "";
        }
        public void endElement(String uri, String localName, String name) throws SAXException {
            checkNeedStop();
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }

            if (name.equals("v")) {
                result.add(lastContents);
                minColumns--;
                if (minColumns<=0){
                    needStop=true;
                }
            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            checkNeedStop();
            lastContents += new String(ch, start, length);
        }
    }

}
