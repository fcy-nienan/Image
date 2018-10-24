package com.fcy.Grammer.Base;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @descripiton: 测试各种拼接字符串的效率
 * @author: fcy
 * @date: 2018-08-20  17:56
 */
public class TestStringContact {
    public static void main(String args[]) {
        testContact();
    }
    public static void testContact(){
        String s="";
        long start=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            s+=i+"";
        }
        long end=System.currentTimeMillis();
        System.out.println("Cose:"+(end-start));

        s="";
        start=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            s.concat(""+i);
        }
        end=System.currentTimeMillis();
        System.out.println("Cost:"+(end-start));

        StringBuilder builder=new StringBuilder();
        start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            builder.append(i);
        }
        end=System.currentTimeMillis();
        System.out.println("Cost:"+(end-start));


        int[][] array=new int[3][5];
        System.out.println(array.length);
        System.out.println(array[1].length);
        System.out.println(" ".charAt(0));
    }
}
