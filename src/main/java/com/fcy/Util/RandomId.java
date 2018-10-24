package com.fcy.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomId {
    public static long getId(){
        long date=System.currentTimeMillis();
        return date;
    }
    public static void test(){
        int x=5;
        try{
            System.out.println('s');
            System.out.println(x/0);
            System.out.println('e');
        }catch (Exception e){

        }
    }
    public static void main(String[] args) {
        String msg="时间:最后更新：2017-07-08 20:52 完结";
        System.out.println(getState(msg));
    }
    public static String getState(String msg){
        String state;
        try{
            state=msg.substring(msg.length()-2,msg.length());
        }catch (Exception e){
            state="正在连载";
        }
        return state;
    }
    public static Date getDate(String msg){
        Pattern pattern=Pattern.compile("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}",2|Pattern.DOTALL);
        Matcher matcher=pattern.matcher(msg);
        String date="";
        while(matcher.find()){
            date=matcher.group();
        }
        Date d;
        try {
            d = simpleDateFormat.parse(date);
        }catch (Exception e){
            d=new Date();
        }
        return d;
    }
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
    public static int getM(int x,int y){
        return x%y==0?x/y:(x/y)+1;
    }
}
