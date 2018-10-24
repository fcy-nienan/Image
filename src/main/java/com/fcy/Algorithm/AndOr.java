package com.fcy.Algorithm;

public class AndOr {
    public static void main(String[] args) {
        System.out.println(8>>1);
        System.out.println(8<<1);
        System.out.println(8<<2);
        int n = 130 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n+1);

    }
}
