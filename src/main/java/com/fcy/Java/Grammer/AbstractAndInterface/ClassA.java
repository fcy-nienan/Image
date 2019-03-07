package com.fcy.Java.Grammer.AbstractAndInterface;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-02  15:32
 */
public class ClassA implements InterfaceA {
    public static StringBuilder builder=new StringBuilder();
    public static void test(StringBuilder builder){
        builder.append(123);
    }
    public static void main(String[] args) {
        StringBuilder builder=new StringBuilder();
        test(builder);
        System.out.println(builder);
    }

}
