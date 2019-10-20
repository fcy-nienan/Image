package com.fcy.Java.Concurrent.CustomerLockDemo;

import sun.misc.DoubleConsts;

import java.io.File;

public class TestNative {
    public static void main(String args[]) throws InterruptedException {
        testNative();
        testNative();
        testNative();

        testOuter();
        testOuter();
        testOuter();


    }
    private static void testNative(){
        long total1=0;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//直接使用本地方法
            total1+= Double.doubleToLongBits(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");
    }
    private static void testOuter(){
        long ttt=0;
        long ttt1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//外部调用本地方法
            ttt = doubleToLongBits(i);
        }
        long ttt2 = System.currentTimeMillis();
        System.out.println(ttt2 - ttt1 + "ms");
    }
    public static long doubleToLongBits(double value) {
        long result =Double.doubleToRawLongBits(value);
        if ( ((result & DoubleConsts.EXP_BIT_MASK) ==
                DoubleConsts.EXP_BIT_MASK) &&
                (result & DoubleConsts.SIGNIF_BIT_MASK) != 0L) {
            result = 0x7ff8000000000000L;
        }
        return result;
    }
}
