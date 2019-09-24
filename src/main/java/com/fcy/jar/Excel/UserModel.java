package com.fcy.jar.Excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private static final String XLSX="xlsx";
    private static final String XLS="xls";
    public static void main(String args[]) throws Exception {
        long start,end;
        start=System.currentTimeMillis();
        try {
            String path = "F:\\1.xlsx";
            int i=0;
            Workbook workbook = getWorkBook(path);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            List<String> list = getRowValue(row);
            i=list.size();
            System.out.println(i);
        }catch(Error error){
            error.printStackTrace();
        }
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

    }
    public static List<String> getRowValue(Row row){
        List<String> list=new ArrayList<>();
        for(Cell c:row){
            list.add(ExcelUtil.getCellStringValue(c));
        }
        return list;
    }
    public static Workbook getWorkBook(String path) throws IOException {
        if (path==null||"".equals(path)){
            throw new IllegalArgumentException("路径不能为空!");
        }
        File file=new File(path);
        FileInputStream inputStream=new FileInputStream(file);
        String suffix=path.substring(path.lastIndexOf(".")+1);
        Workbook workbook=null;
        if (suffix.equals(XLS)) {
            workbook=new HSSFWorkbook(inputStream);
        }else if(suffix.equals(XLSX)){
            workbook=new XSSFWorkbook(inputStream);
        }else{
            throw new IllegalArgumentException("不合法的文件名!");
        }
        return workbook;
    }
}
