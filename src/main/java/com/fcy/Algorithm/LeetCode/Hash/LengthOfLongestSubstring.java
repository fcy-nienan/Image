package com.fcy.Algorithm.LeetCode.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-25  22:02
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s="abcabcced";
        int len=lengthOfLongestSubstring(s);
        len=lengthOfLongestSubstringTwoPointer(s);
        len=lengthOfLongestSubstringArray(s);
        System.out.println(len);
    }
    public static int lengthOfLongestSubstring(String s){
        int len=0;
        Map<Character,Integer> map=new HashMap<>();
        int i=0;
        for(int j=0;j<s.length();j++){
            Character c=s.charAt(j);
            if (map.containsKey(c)){
//                i=Math.max(map.get(c),i)+1;
                int pre=map.get(c);
                if (pre>=i){
                    i=pre+1;
                }
            }
            map.put(c,j);
            len=Math.max(len,j-i+1);
        }
        return len;
    }
    public static int lengthOfLongestSubstringTwoPointer(String s){
        int len=0;
        int left=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            for(int index=left;index<i;index++){
                if (s.charAt(index)==c){
                    left=index+1;
                }
            }
            len=Math.max(len,i-left+1);
        }
        return len;
    }
    public static int lengthOfLongestSubstringArray(String s){
        int[] array=new int[128];
        int left=0;
        int len=0;
        for(int i=0;i<s.length();i++){
            left=Math.max(array[s.charAt(i)],left);
            len=Math.max(i-left+1,len);
            array[s.charAt(i)]=i+1;
        }
        return len;
    }
    public static int lengthOfLgestSubstringArrayTwo(String s){
        int[] array=new int[128];
        for(int i=0;i<array.length;i++){
            array[i]=-1;
        }
        int left=0;
        int len=0;
        for(int i=0;i<s.length();i++){
            if (array[s.charAt(i)]>=left){
                left=array[s.charAt(i)]+1;
            }
            len=Math.max(len,i-left+1);
            array[s.charAt(i)]=i;
        }
        return len;
    }
}
