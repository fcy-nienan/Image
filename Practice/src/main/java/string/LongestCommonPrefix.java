package string;

import java.util.HashMap;

/*
*
*
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

 

示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。

*
*
*解题思路:
* 之前想着通过一个下标,在每个不同的数组向右走,后来写起来才发现不行
* 需要比较N个值是否相同  s1[i] == s2[i] == s3[i] == s4[i] .........
* 单纯的循环无法做到,可能需要借助其他数据结构
*
* 然后换了一种思路,直接暴力,将其他数组的值与第一个数组循环比较
* 然后判断的时候判断可能会导致比较中断的条件
*
*
* */
public class LongestCommonPrefix {
    public static void main(String[] args) {

    }
    public String longestCommonPrefix(String[] strs) {
        int[] x = new int[strs.length];
        for (int i=0;i<strs[0].length();i++){

        }
        if (strs==null){
            return "";
        }
        if (strs.length==1){
            return strs[0];
        }
        for (int i=0;i<strs[0].length();i++){
            char c = strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                String s = strs[j];
                if (i+1>s.length()){
                    return strs[0].substring(0,i);
                }
                if (s.charAt(i)!=c){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
}
