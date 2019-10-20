package com.fcy.Java.Concurrent.CustomerLockDemo;

import sun.misc.DoubleConsts;

import java.io.File;

public class TestNative {
    public static void main(String args[]) throws InterruptedException {
        long total1=0;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total1+= Double.doubleToLongBits(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");

        long total2=0;
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total2 = doubleToLongBits(i);
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "ms");


        long ttt=0;
        long ttt1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ttt = doubleToLongBits(i);
        }
        long ttt2 = System.currentTimeMillis();
        System.out.println(ttt2 - ttt1 + "ms");


        long total3=0;
        long t5 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total2 = Double.doubleToLongBits(i);
        }
        long t6 = System.currentTimeMillis();
        System.out.println(t6 - t5 + "ms");





        System.out.println(total1+total2);
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
