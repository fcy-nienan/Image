package com.fcy.jar.pdf;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * @author CharlieChen
 * @DateTime 2015-1-5 上午9:55:38
 * @version 1.0
 */
public class PdfUtil {
    private static String PDFPATH = "D:\\BOOK\\书籍\\net\\新建文件夹\\Linux\\Linux内核_39.pdf";
    private static String FILEPATH = "D:/Maven权威指南中文版.doc";
    public static void main(String[] args) throws Exception {
        String content=getPdfContent(PDFPATH);
        System.out.println(new String(content.getBytes("ISO-8859-1"), "gbk"));
//        System.out.println(content);
//        toFile(content,FILEPATH);
    }
    /**
     * 获取pdf的内容<br/>
     * @param pdfPath
     * @return
     * @throws Exception
     */
    private static String getPdfContent(String pdfPath) throws Exception {
        boolean sort = false;// 是否排序
        int startPage = 1;// 开始提取页数
        int endPage = Integer.MAX_VALUE;   // 结束提取页数
        String content = null;//暂时存放pdf内容

        InputStream input = null;
        File pdfFile = new File(pdfPath);
        PDDocument document = null;
        try {
            input = new FileInputStream(pdfFile);
            // 加载 pdf 文档
            PDFParser parser = new PDFParser(input);
            parser.parse();
            document = parser.getPDDocument();
            // 获取内容信息
            PDFTextStripper pts = new PDFTextStripper();
            pts.setSortByPosition(sort);
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(1);
            try {
                content = pts.getText(document);
            }catch(Exception e) {
                throw e;
            }
            System.out.println("Get PDF Content ...");
        }catch(Exception e){
            throw e;
        } finally {
            if (null != input)
                input.close();
            if (null != document)
                document.close();
        }
        return content;
    }
    private static void toFile(String content,String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            System.out.println("Write PDF Content to txt file ...");
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(content);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}