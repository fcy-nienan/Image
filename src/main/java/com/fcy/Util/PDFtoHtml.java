package com.fcy.Util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class PDFtoHtml{
    public static void main(String[] args) {
        pdfToHtml();
    }
    /**
     * pdf转html
     * @param
     * @return
     */
    private static int pdfToHtml(){
        int rv = 0;
        //文件夹名
        String folderName = UUID.randomUUID().toString();
        String wordPath="G:\\pdf";
        String path="G:\\pdf\\001.PDF";
        List<String> imgList = new ArrayList<String>();
        try {
            PDDocument doc = PDDocument.load(path);
            int pageCount = doc.getPageCount();
            System.out.println(pageCount);
            List pages = doc.getDocumentCatalog().getAllPages();
            for(int i=0;i<pages.size();i++){
                PDPage page = (PDPage)pages.get(i);
                BufferedImage image = page.convertToImage();
                Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer = (ImageWriter)iter.next();
                String imgName = File.separator + folderName + File.separator +i+".jpg";
                File folder = new File(wordPath + File.separator + folderName); //先创建文件夹
                folder.mkdirs();
                File outFile = new File(wordPath + imgName);    //再创建文件
                imgList.add(File.separator + "upload" + imgName);
                outFile.createNewFile();
                FileOutputStream out = new FileOutputStream(outFile);
                ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(image,null,null));
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return rv;
        } catch (IOException e) {
            e.printStackTrace();
            return rv;
        }
        rv = createHtml("G:\\html", imgList);
        return 1;
    }
    private static int createHtml(String wordPath,List<String> imgList){
        int rv = 0;
        StringBuilder sb = new StringBuilder("<!doctype html><html><head><meta charset='utf-8'><title>无标题文档</title></head><body>");
        if (imgList != null && !imgList.isEmpty()) {
            for (String img : imgList) {
                sb.append("<img src='" + img + "' /><br>");
            }
        }
        sb.append("</body></html>");

        String uuid = UUID.randomUUID().toString();
        try {
            File file = new File(wordPath + File.separator + uuid + ".html");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return rv;
        }
//        presentationDto.setHtmlPath(tempContextUrl + "upload" + File.separator + uuid+".html");
        return 1;
    }
}