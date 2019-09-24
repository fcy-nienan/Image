package com.fcy.jar.Excel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReadExcelHead {
    public static void main(String[] args) {
        List<String> headValue = getHeadValue("F:\\2.xls", 27);
        headValue.forEach(e->{
            System.out.println(e);
        });
    }
    public static List<String> getHeadValue(String path,int maxColumn){
        List<String> result=new ArrayList<>();
        String type=path.substring(path.lastIndexOf(".")+1);
        try {
            if (type.equals("xls")) {
                result = EventModelXLS.getFirstRow(path, maxColumn);
            } else if (type.equals("xlsx")) {
                result = EventModelXLSX.processOneSheet(path, maxColumn);
            } else {
                throw new IllegalArgumentException("不合法的文件类型!");
            }
        }catch (Exception ee){
            ee.printStackTrace();
        }
        return result;
    }
}
