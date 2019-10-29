package com.fcy.Algorithm.LeetCode.ArrayIndex;

/**
 * @descripiton:
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案
 *
 * 输入: "cbbd"
 * 输出: "bb"
 * @author: fcy
 * @date: 2019-03-02  19:50
 */
public class LongestPalindrome {
    private static int start=0,maxLen=0;
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("bb"));
    }
    private static void extend(String s,int left,int right){
        while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        if (maxLen<(right-left-1)){
            start=left+1;
            maxLen=(right-left-1);
        }
    }
    public static String longestPalindrome(String s) {
        if (s.length()<2)
            return s;
        maxLen=0;start=0;
        for(int i=0;i<s.length();i++){
            extend(s,i,i);
            extend(s,i,i+1);
        }
        char[] source=s.toCharArray();
        char[] des=new char[maxLen];
        System.arraycopy(source,start,des,0,maxLen);
        return new String(des);
    }
}
