package com.fcy.Api.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class TestDataProdcer {
    private static Logger logger = Logger.getLogger(TestDataProdcer.class.getName());

    public static void main(String args[]) throws Exception {
        long z=1;
        long k=1;
        System.out.println(k<<32);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((k<<32)/1024/1024/1024);
        System.out.println(Long.MAX_VALUE/1024/1024/512);
//        for (int j=0;j<10;j++) {
//            List<rowData> list = new ArrayList<>();
//            for (int i = 0; i < 1000000; i++) {
//                String vin = GeneratedCarID.generateCarID();
//                String vehicle_license = VinGenerator.getRandomVin();
//                String idcard = IdCardGenerator.generate();
//                String start = getRandomDate();
//                String end = getRandomDate();
//                rowData rowData = new rowData(vin, vehicle_license, idcard, start, end);
//                list.add(rowData);
//            }
//            System.out.println("add data finished and start to write !");
//            String dst = "E:\\Big"+j+".xlsx";
//            write(dst, list);
//        }

    }
    private static String getRandomDate(){
        Random rndYear=new Random();
        int year=rndYear.nextInt(18)+2000;  //生成[2000,2017]的整数；年
        Random rndMonth=new Random();
        int month=rndMonth.nextInt(12)+1;   //生成[1,12]的整数；月
        Random rndDay=new Random();
        int Day=rndDay.nextInt(30)+1;       //生成[1,30)的整数；日
//        Random rndHour=new Random();
//        int hour=rndHour.nextInt(23);       //生成[0,23)的整数；小时
//        Random rndMinute=new Random();
//        int minute=rndMinute.nextInt(60);   //生成分钟
//        Random rndSecond=new Random();
//        int second=rndSecond.nextInt(60);   //秒
        return year+"-"+month+"-"+Day;
    }
    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class rowData{
        private String vin;
        private String vehicle_license;
        private String idCard;
        private String start;
        private String end;
    }
    public static void write(String fileName, List<rowData> list)  {
        String fixx=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        Workbook workbook=null;
        if (fixx.equals("xls")){
            workbook = new HSSFWorkbook();
        }else if (fixx.equals("xlsx")){
            workbook = new XSSFWorkbook();
        }else{
            System.out.println("invalid type:"+fileName);
            return;
        }

        Sheet sheet = workbook.createSheet("StudentScore");

        // 创建Excel标题行，第一行
        Row headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("车牌号");
        headRow.createCell(1).setCellValue("车架号");
        headRow.createCell(2).setCellValue("身份证号");
        headRow.createCell(3).setCellValue("屏蔽开始日期");
        headRow.createCell(4).setCellValue("屏蔽结束日期");


        // 往Excel表中遍历写入数据
        for (rowData studentScore : list) {
            createCell(studentScore, sheet);
        }

        File xlsFile = new File(fileName);
        try {
            workbook.write(new FileOutputStream(xlsFile));
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }
    }

    // 创建Excel的一行数据。
    private static void createCell(rowData studentScore, Sheet sheet) {
        Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        dataRow.createCell(0).setCellValue(studentScore.getVin());
        dataRow.createCell(1).setCellValue(studentScore.getVehicle_license());
        dataRow.createCell(2).setCellValue(studentScore.getIdCard());
        dataRow.createCell(3).setCellValue(studentScore.getStart());
        dataRow.createCell(4).setCellValue(studentScore.getEnd());
    }
}
