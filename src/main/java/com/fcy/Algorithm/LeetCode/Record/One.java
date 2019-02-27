package com.fcy.Algorithm.LeetCode.Record;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-18  2:56
 */
public class One {
    public static void main(String[] args) {
        System.out.println(strStr("hello","ll"));
        System.out.println(strStr("mississippi","issip"));
        "hello".indexOf("ll");
    }
//    输入: haystack = "hello", needle = "ll"
//    输出: 2
    public static int strStr(String haystack, String needle) {
        if (needle==null)
            return -1;
        if (needle.equals(""))
            return 0;
        if (haystack.equals(needle))
            return 0;
        char[] hay=haystack.toCharArray();
        char[] need=needle.toCharArray();
        int start=0,end=0,index=0;
        for(int i=0;i<hay.length;i++){
            if (need[index]==hay[i]){
                end++;
                index++;
            }else{
                index=0;
                start++;
                end++;
            }
            if (index==(need.length-1))
                return start;
        }
        return -1;
    }
}
