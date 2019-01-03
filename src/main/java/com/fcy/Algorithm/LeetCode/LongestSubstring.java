package com.fcy.Algorithm.LeetCode;

import java.util.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-30  22:47
 */
public class LongestSubstring {
    public static void main(String[] args) {
        String s="abcabcbb";

        System.out.println(violentCracking(s));
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(SlidingWindow(s));
        System.out.println(optimiSlidingWindow(s));
    }
    /*
    * 暴力
    * */
    public static int violentCracking(String s){
        int len=0;
        List<Character> list=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                Character character=s.charAt(j);
                if (list.contains(character)){
                    len=Math.max(len,list.size());
                    list.clear();
                    continue;
                }else{
                    list.add(character);
                    len=Math.max(len,list.size());
                }
            }
        }
        return len;
    }
    /*
    * 滑动窗口
    * */
    public static int SlidingWindow(String s){
        int len=0;
        int i=0,j=0;
        HashSet<Character> set=new HashSet<>();
        while (i<s.length()&&j<s.length()){
            Character character=s.charAt(j);
            if (set.contains(character)){
                set.remove(s.charAt(i++));//优化的滑动窗口在这步,如果能直接移除上一个重复的字符,然后i直接跳到这里
//                当时有疑问只移除第一个有用吗,后来想到移除第一个后set中还有改字符,所以又会进入if语句进行移除
//                优化的互动窗口就是用一个HashMap来保存对于字符的索引,所以如果存在改字符就直接把该字符的索引+1
//                赋值给i
            }else{
                set.add(character);
                j++;
                len=Math.max(len,j-i);
            }
        }
        return len;
    }
    public static int optimiSlidingWindow(String s){
//        String s="abcabcbb";
        int len=0;
        int i=0,j=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for(;j<s.length();){
            Character character=s.charAt(j);
            if (map.containsKey(character)){
//                i=map.get(character)+1;
//                i=Math.max(i,map.get(character))+1;
                i=Math.max(i,map.get(character));
            }
            len=Math.max(len,j-i+1);
            map.put(character,j);
            j++;
//            len=Math.max(len,j-i);
        }
        return len;
    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
