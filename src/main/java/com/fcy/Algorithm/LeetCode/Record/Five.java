package com.fcy.Algorithm.LeetCode.Record;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-20  21:44
 */
public class Five {
    public static void main(String[] args) {
        System.out.println(reverse("abcdefhijk"));
    }
    public static String reverse(String s){
        char[] value=s.toCharArray();
        int count=value.length;
        int n = count - 1;
        for (int j = (n-1) >> 1; j >= 0; j--) {
            int k = n - j;
            char cj = value[j];
            char ck = value[k];
            value[j] = ck;
            value[k] = cj;
        }
        return new String(value);
    }
}
