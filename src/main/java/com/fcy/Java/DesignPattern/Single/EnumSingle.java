package com.fcy.Java.DesignPattern.Single;

/**
 * @descripiton: 枚举类型的单例，但失去了一些类的特性
 * @author: fcy
 * @date: 2018-08-02  9:38
 */
public enum EnumSingle {
    instance;
    public static EnumSingle getInstance(){
        return instance;
    }
}
