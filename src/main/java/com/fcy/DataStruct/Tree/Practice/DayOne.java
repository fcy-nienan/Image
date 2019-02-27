package com.fcy.DataStruct.Tree.Practice;

import com.fcy.DataStruct.Tree.Traversing;
import com.fcy.DataStruct.Tree.TreeNode;

import java.util.*;

public class DayOne {
    public static void main(String[] args) {
//        97 97     98 97 97
        System.out.println(checkInclusion("aa", "baa"));
        System.out.println(findAnagrams("baa","aa"));
    }
    /**
    *@descripiton s1:abc,s2:kjlksdfbac return true  s1的排列组合在s2中出现过(abc->bac)
    */
//    总体思路还是用hash
    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int[] count1 = new int[26]; // s1每个字符出现的次数
        int[] count2 = new int[26]; // s2每个字符出现的次数
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        int[] diff = new int[26]; // s1和s2每个字符的数量差距
        for (int i = 0; i < diff.length; i++) {
            diff[i] = count2[i] - count1[i];
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            if(isSame(diff)) {
                return true;
            }
            diff[s2.charAt(i - s1.length()) - 'a']--; // 去掉首个字符
            diff[s2.charAt(i) - 'a']++; // 添加最新的字符
        }
        return isSame(diff);
    }
    public static boolean isSame(int[] diff) {
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] != 0) {
                return false;
            }
        }
        return true;
    }
//    返回一个字符串的所有排列组合(相同长度的)
//    解题思路：本题求整个字符串的全排列可以看做两步
//    1）首先求出所有可能出现在第一位置的字母，即begin与后面所有与它不同的字母进行交换
//    2）固定第一个字母，求后面字母的全排列，即递归此时begin = begin+1
    public static void getAllCombine(int i,char[] chars,List list){
        if (i==chars.length-1){
            String value=new String(chars);
            if (!list.contains(value)){
                list.add(value);
            }
        }
        for(int j=i;j<chars.length;j++){
            swap(i,j,chars);
            getAllCombine(i+1,chars,list);
            swap(j,i,chars);
        }
    }
    public static void swap(int i,int j,char[] chars){
        char tmp=chars[i];
        chars[i]=chars[j];
        chars[j]=tmp;
    }
//    找出异或字符    两个点:    字符映射    滑动窗口
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> integers=new ArrayList<>();
        if (s==null||p==null||p.length()>s.length())
            return new ArrayList<>();
        int[] array=new int[26];
        int[] count2=new int[26];
        for(int i=0;i<p.length();i++){
            array[p.charAt(i)-'a']++;
            count2[s.charAt(i)-'a']++;
        }
        int[] diff=new int[26];
        for(int i=0;i<diff.length;i++){
            diff[i]=count2[i]-array[i];
        }
        for(int i=p.length();i<s.length();i++){
            System.out.println("sdf"+i);
            if (isSame(diff)){
                int tmp=i-p.length();
                integers.add(tmp);
            }
            diff[s.charAt(i-p.length())-'a']--;
            diff[s.charAt(i)-'a']++;
            if (i==s.length()-1){
                if (isSame(diff)){
                    int tmp=i-p.length()+1;//必须加1
                    integers.add(tmp);
                }else{
                }
            }
        }
        return integers;
    }
//    t是否是s的异位词
//    输入: s = "rat", t = "car"  115 97 117   99 97 115
//    输出: false
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length())
            return false;
        int[] array=new int[26];
        for(int i=0;i<s.length();i++){
            array[s.charAt(i)-97]++;
        }
        for(int i=0;i<t.length();i++){
            if (array[t.charAt(i)-97]==0)
                return false;
            else
                array[t.charAt(i)-97]--;
        }
        return true;
    }
//    字母异位词分组
//    输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
//    输出:
//            [
//            ["ate","eat","tea"],
//            ["nat","tan"],
//            ["bat"]
//            ]
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String s:strs){
//            这个key值可以优化,这里是通过排序,还可以通过其他方式生成唯一的key
            char[] tmp=s.toCharArray();
            Arrays.sort(tmp);
            String key=new String(tmp);
            if (map.containsKey(key)){
                map.get(key).add(s);
            }else{
                List<String> array=new ArrayList<>();
                array.add(s);
                map.put(key,array);
            }
        }
//        for(Map.Entry<String,List<String>> entry:map.entrySet()){
//            listList.add(entry.getValue());
//        }
        return new ArrayList<>(map.values());
    }
}
