package com.fcy.Password;

import com.fcy.Java.JDBC.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-17  12:00
 */
public class QQ {
    private static String solt="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*()-=_+[]{},./<>?1234567890";
    public static void main(String[] args) throws SQLException {
        pwDao pwDao=new pwDao();
        pwDao.add(getPassword(22,15));
        System.out.println(pwDao.query(0l));
    }
    private static pw getPassword(int seed,int pwLen){
        int len=solt.length();
        StringBuilder p=new StringBuilder();
        Random random=new Random(seed);
        for(int i=0;i<pwLen;i++){
            int next=random.nextInt(len);
            p.append(solt.charAt(next));
        }
        return new pw(0,"qq",p.toString(),new Date().toString());
    }
}
