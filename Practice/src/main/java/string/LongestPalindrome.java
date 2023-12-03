package string;
/*
*
给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"

*
*
*
*
* 解题思路:
* 遍历字符串数组,对每个字符开始扩展
* 分两种情况
* 1 中间只有一个字符  aba
* 2 中间有两个字符   dbbd
*
*
* */
public class LongestPalindrome {
    public static void main(String[] args) {

    }
    public static int extend(String str,int left,int right){
        while(left >=0 && right <str.length() && str.charAt(left)==str.charAt(right)){
            left --;
            right ++;
        }
        return 1;
    }

}
