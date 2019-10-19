package com.fcy.Java.Concurrent.CustomerLockDemo;

import sun.misc.DoubleConsts;

import java.io.File;

public class TestNative {
    public static void main(String args[]) throws InterruptedException {
        Object o=new Object();
        long start=System.currentTimeMillis();
        synchronized (o){
            o.wait(1000);
            long end=System.currentTimeMillis();
            System.out.println(end-start+ "ts");
        }


        long total1=0;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total1+= Double.doubleToRawLongBits(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");

        long total2=0;
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total2 = Double.doubleToLongBits(i);
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "ms");
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
