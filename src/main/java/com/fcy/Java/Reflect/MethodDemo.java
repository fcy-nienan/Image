package com.fcy.Java.Reflect;

import java.lang.reflect.Method;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-18  1:40
 */
public class MethodDemo {
    public static void main(String[] args) {
        Method[] methods = MethodDemo.class.getMethods();
    }
}
