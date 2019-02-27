package com.fcy.Algorithm.LeetCode;

import java.net.Socket;
import java.util.Arrays;
import java.util.Stack;

/**
 * @descripiton:回文数
 * @author: fcy
 * @date: 2018-12-08  0:31
 */
public class PalindromeString {
    public static void main(String[] args) {//101 010
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("0P"));
    }
    public static boolean isP(String str){
        int[] lo=new int[37];
        str=str.toLowerCase();
        int len=0;
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if ((c>=97&&c<=122)) {
                lo[c - 97]++;
                len++;
            }
            if(c>=48&&c<=57){
                System.out.println(c-12);
                lo[c-22]++;
                len++;
            }
        }
        int count=0;
        if (len%2==0){
            for(int i:lo){
                if (i%2!=0)
                    return false;
            }
        }else{
            for(int i:lo){
                if (i%2!=0)
                    count++;
            }
            if (count>1)
                return false;
        }
        return true;
    }
    public static boolean isPalindrome(String str){//judge whether is a palindrome
        str=str.toLowerCase();
        char[] chars=new char[str.length()];
        int count=0;
        for(char c:str.toCharArray()){
            if ((c>=48&&c<=57)||(c>=97&&c<=122)){
                chars[count++]=c;
            }
        }
        int start=0,end=count-1;
        while (start<=end){
            if (chars[start]==chars[end]){
                start++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }

}
