package com.fcy.Java.Base;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-03  17:20
 */
public class StringDemo {
    public static void main(String[] args) {
        String s="www.baidu.com";
        StringBuilder  builder;
        String t = s.replaceAll("\\S", "t");
        System.out.println(t);
    }
}
