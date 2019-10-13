package com.fcy.Java.Grammer;

import java.lang.reflect.Field;

public class  DefaultValue{
    private boolean flag;
    private int intValue;
    private long longValue;
    private short shortValue;
    private float floatValue;
    private double doubleValue;
    private String stringValue;
    private byte byteValue;
    private char charValue;
    public void display(DefaultValue value){
        Class clazz=DefaultValue.class;
        Field[] fields=clazz.getDeclaredFields();
        for (Field f:fields){
            f.setAccessible(true);
            try {
                System.out.println(f.getType()+" default value:"+f.get(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]) {
        DefaultValue value=new DefaultValue();
        value.display(value);
    }
}
