package com.fcy.Java.Grammer;

import java.lang.reflect.Field;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-12  0:05
 */
public class SwapInteger {
    public static void main(String[] args) {
        Integer one=1,two=2;
        System.out.println(one+":"+two);
        swapReflect(one,two);
        System.out.println(one+":"+two);

        Long l1=10l,l2=20l;
        swapLong(l1,l2);
        System.out.println(l1+":"+l2);
    }
    private static void swap(Integer one,Integer two){
        one=5555;
        two=46666;
    }
    private static void swapReflect(Integer one,Integer two){
        try{
            Integer tmp=new Integer(one.intValue());
            Field field=one.getClass().getDeclaredField("value");
            field.setAccessible(true);
            field.setInt(one,two.intValue());

            Field field1=two.getClass().getDeclaredField("value");
            field1.setAccessible(true);
            field1.setInt(two,tmp.intValue());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private static void swapLong(Long l1,Long l2){
        l1=100l;
        l2=200l;
    }
}
