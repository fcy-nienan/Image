package com.fcy.jar.pdf;

import java.io.*;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
/**
 * @author CharlieChen
 * @DateTime 2015-1-5 上午11:43:26
 * @version 1.0
 */
public class iTextUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String pdfPath = "D:\\BOOK\\书籍\\Linux内核_79.pdf";
        String txtfilePath = "D:/temp/成交单-PDF格式-itext.txt";

        //readPdfToTxt(pdfPath,txtfilePath); //调用读取方法
        System.out.println(readPdfToTxt(pdfPath));
        System.out.println("Finished !");

    }

    /**
     * 读取PDF文件内容到txt文件
     *
     * @param txtfilePath
     * @param pdfPath
     */
    private static void readPdfToTxt(String pdfPath,String txtfilePath) {
        // 读取pdf所使用的输出流
        PrintWriter writer = null;
        PdfReader reader = null;
        try {
            writer = new PrintWriter(new FileOutputStream(txtfilePath));
            reader = new PdfReader(pdfPath);
            int num = reader.getNumberOfPages();// 获得页数
            System.out.println("Total Page: " + num);
            String content = ""; // 存放读取出的文档内容
            for (int i = 1; i <= num; i++) {
                // 读取第i页的文档内容
                content += PdfTextExtractor.getTextFromPage(reader, i);
            }
            String[] arr = content.split("/n");
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            /*String[] childArr = arr[i].split(" ");
            for(int j=0;j<childArr.length;j++){
                System.out.println(childArr[j]);
            }*/
            }

            //System.out.println(content);

            writer.write(content);// 写入文件内容
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取pdf内容
     * @param pdfPath
     */
    private static String readPdfToTxt(String pdfPath) {
        PdfReader reader = null;
        StringBuffer buff = new StringBuffer();
        try {
            reader = new PdfReader(pdfPath);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            int num = reader.getNumberOfPages();// 获得页数
            TextExtractionStrategy strategy;
            for (int i = 1; i <= num; i++) {
                strategy = parser.processContent(i,
                        new SimpleTextExtractionStrategy());
                buff.append(strategy.getResultantText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buff.toString();
    }

}
