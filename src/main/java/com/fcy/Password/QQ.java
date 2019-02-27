package com.fcy.Password;

import java.util.Arrays;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-17  12:00
 */
public class QQ {
    private static String solt="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*()-=_+[]{},./<>?1234567890";
    public static void main(String[] args) {
        System.out.println(getPassword(22,15));
    }
    private static String getPassword(int seed,int pwLen){
        int len=solt.length();
        StringBuilder p=new StringBuilder();
        Random random=new Random(seed);
        for(int i=0;i<pwLen;i++){
            int next=random.nextInt(len);
            p.append(solt.charAt(next));
        }
        return p.toString();
    }
}
