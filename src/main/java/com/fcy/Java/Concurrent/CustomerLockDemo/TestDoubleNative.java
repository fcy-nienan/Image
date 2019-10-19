package com.fcy.Java.Concurrent.CustomerLockDemo;

public class TestDoubleNative {
    public static void main(String args[]) {
        long start,end;
        start = System.currentTimeMillis();
        for (long i=0;i<Integer.MAX_VALUE;i++){
            double l=Double.longBitsToDouble(i);
        }
        end=System.currentTimeMillis();
        System.out.println(end-start + "ts");



    }
}
