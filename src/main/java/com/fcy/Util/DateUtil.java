package com.fcy.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil {
    private static Logger logger = Logger.getLogger(DateUtil.class.getName());

    public static void main(String args[]) throws Exception {
        System.out.println(getDay(new Date()));
    }
    public static String getDay(Date date){
        return String.format("%tI",date);
    }
}
