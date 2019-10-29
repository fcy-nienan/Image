package com.fcy.Algorithm.LeetCode.Recursive;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-26  0:15
 */
public class OneNineEqualNumber {
    public static int len(String s,int des){
        if (s.length()==2){
            int a=Integer.parseInt(s.substring(0,1));
            int b=Integer.parseInt(s.substring(1,2));
            int ab=Integer.parseInt(s);
            if (a+b==des||a-b==des||ab==des)
                return 1;
            else
                return 0;
        }
        int len=0;
        String nextString = s.substring(0, s.length() - 1);
        String regularString = s.substring(s.length() - 1);
        int regular = Integer.parseInt(regularString);
        len += len(nextString, des + regular);
        len += len(nextString, des - regular);

        String ssStr = s.substring(0, s.length() - 1);
        String ss = s.substring(s.length() - 2);
        int ssNum = Integer.parseInt(ss);
        len += len(ssStr, des + ssNum);
        len += len(ssStr, des - ssNum);

        return len;
    }

    public static void main(String[] args) {
//        123456789=100
        String s="123456789";
        System.out.println(len(s,100));
    }
}
