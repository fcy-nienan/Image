package com.fcy.Algorithm.LeetCode;

import java.net.Socket;

/**
 * @descripiton:回文数
 * @author: fcy
 * @date: 2018-12-08  0:31
 */
public class PalindromeString {
    public static void main(String[] args) {//101 010
        System.out.println(getPalindrome("abbcasdf"));
    }
    public static boolean isPalindrome(String str){//judge whether is a palindrome
        boolean flag;
        char[] chars=str.toCharArray();
        int len=str.length(),j=0;
        while (j<(len/2)&&chars[j]==chars[len-j-1])
            j++;
        if (j==(len/2))
            flag=true;
        else
            flag=false;
        return flag;
    }
    public static String getPalindrome(String str){//get longest palindrome
        String result;
        char[] chars=str.toCharArray();
        int m=0,n=0,dis=0;
        for(int i=0;i<chars.length;i++){
            for(int j=i+1;j<chars.length;j++){
                if (isPalindrome(new String(chars,i,j))){
                    if (dis<(j-i)){
                        dis=j-i;
                        m=j;n=i;
                    }
                }
            }
        }
        result=new String(chars,n,m);
        return result;
    }
}
