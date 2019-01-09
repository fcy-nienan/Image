package com.fcy.jar.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-28  1:24
 */
public class ExcelUtil {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String excel_name="D:\\user.xlsx";
        String[] headList=new String[]{"姓名","密码","邮箱","电话号码","出生日期"};
        String[] fieldList=new String[]{"username","password","email","phone","birth"};
        List<Map<String,Object>> dataList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("username","fcy"+i);
            map.put("password","521314"+i);
            map.put("email","807715333@qq.com");
            map.put("phone","18279516912");
            map.put("birth",dateFormat.format(new Date()));
            dataList.add(map);
        }
        createExcel(excel_name,headList,fieldList,dataList);

        File file=new File(excel_name);
        FileInputStream outputStream=new FileInputStream(file);
        List<List<Object>> lists=readExcel(outputStream);
        System.out.println(lists);
        lists.forEach(e->{
            e.forEach(j->{
                System.out.println(j);
            });
        });
    }
    /**
     * 要求excel版本在2007以上
     *
     * @param fileInputStream 文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(FileInputStream fileInputStream) throws Exception {
        List<List<Object>> list = new LinkedList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(fileInputStream);
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;


        System.out.println(sheet.getFirstRowNum());
        System.out.println(sheet.getLastRowNum());
        System.out.println(sheet.getPhysicalNumberOfRows());
        Iterator iterator=sheet.iterator();
        while(iterator.hasNext()){
            Row row1= (Row) iterator.next();
            Iterator iterator1=row1.cellIterator();
            while(iterator1.hasNext()){
                Cell cell1= (Cell) iterator1.next();
                System.out.println(cell1.getStringCellValue());
            }
        }


        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString())) {
                            System.out.println(cell.getNumericCellValue()+":日期格式："+cell.getCellStyle().getDataFormatString());
//                            value = DateUtils.getMillis(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) / 1000;
                        } else {
                            //System.out.println(cell.getNumericCellValue()+":格式："+cell.getCellStyle().getDataFormatString());
                            value = cell.getNumericCellValue();
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        break;
                    default:
                        value = cell.toString();
                }
                if (value != null && !value.equals("")) {
                    //单元格不为空，则加入列表
                    linked.add(value);
                }
            }
            if (linked.size()!= 0) {
                list.add(linked);
            }
        }
        return list;
    }

    public static void createExcel(String excel_name, String[] headList,
                                   String[] fieldList, List<Map<String, Object>> dataList)
            throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();
        // 在索引0的位置创建行（最顶端的行）
        XSSFRow row = sheet.createRow(0);
        // 设置excel头（第一行）的头名称
        for (int i = 0; i < headList.length; i++) {

            // 在索引0的位置创建单元格（左上端）
            XSSFCell cell = row.createCell(i);
            // 定义单元格为字符串类型
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            // 在单元格中输入一些内容
            cell.setCellValue(headList[i]);
        }
        // ===============================================================
        //添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引1的位置创建行（最顶端的行）
            XSSFRow row_value = sheet.createRow(n + 1);
            Map<String, Object> dataMap = dataList.get(n);
            // ===============================================================
            for (int i = 0; i < fieldList.length; i++) {

                // 在索引0的位置创建单元格（左上端）
                XSSFCell cell = row_value.createCell(i);
                // 定义单元格为字符串类型
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                // 在单元格中输入一些内容
                cell.setCellValue((dataMap.get(fieldList[i])).toString());
            }
            // ===============================================================
        }
        File file=new File(excel_name);
        if (!file.exists()){
            file.createNewFile();
        }
        // 新建一输出文件流
        FileOutputStream fos = new FileOutputStream(file);
        // 把相应的Excel 工作簿存盘
        workbook.write(fos);
        fos.flush();
        // 操作结束，关闭文件
        fos.close();
    }
}
