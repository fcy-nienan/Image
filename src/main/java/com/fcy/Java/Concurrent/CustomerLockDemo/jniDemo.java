package com.fcy.Java.Concurrent.CustomerLockDemo;

public class jniDemo{
    static{
        System.loadLibrary("testSO");
    }
    public int incrementX(int x){
        return increment(x);
    }
    public native int increment(int x);
}