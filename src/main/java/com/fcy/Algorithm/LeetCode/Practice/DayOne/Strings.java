package com.fcy.Algorithm.LeetCode.Practice.DayOne;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-06  21:41
 */
public class Strings {
    public static void main(String[] args) {
        System.out.println(FindNoRepeatLength("abcabbcdedfsa"));
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(maxSubSequence(new int[]{5,5,-3,2,3,4}));//10
        System.out.println(maxLengthIncrementSequence(new int[]{1,3,5,2,-8}));
        System.out.println(maxLengthIncrementSequence(new int[]{6,4,8,2,17}));
        System.out.println(maxLengthIncrementSequence(new int[]{89 ,256, 78, 1, 46, 78, 8}));
        System.out.println();
        DismaxLengthIncrementSequence(new int[]{6,4,8,2,17});
        DismaxLengthIncrementSequence(new int[]{80,256,78,1,46,78,8});
    }
    /*
    * 查找无重复字符的最长子串
    * 滑动窗口
    * 维持两个指针和一个Set容器
    * 当没出现相同的字符的时候右指针右移动
    * 当出现相同的字符的时候移除第一个,同时左指针左移动
    * */
    public static int FindNoRepeatLength(String s){
        int len=0;
        int i=0,j=0;
        HashSet<Character> characterHashSet=new HashSet<>();
        while (j<s.length()){
            Character character=s.charAt(j);
            if (characterHashSet.contains(character)){
                characterHashSet.remove(s.charAt(i++));
            }else{
                characterHashSet.add(character);
                j++;
                len=Math.max((j-i),len);
            }
        }
        return len;
    }
    public static int optimiticsSlideWindow(String s){
        int len=0;
        HashMap<Character,Integer> map=new HashMap<>();
        int i=0,j=0;
        while (j<s.length()){
            System.out.println(i+":"+j+":"+map.size());
            if (map.containsKey(s.charAt(j))){
//                int index=map.get(s.charAt(j));
//                i=index+1;
//                map.remove(s.charAt(j));
                i=Math.max(i,map.get(s.charAt(j)));
            }else{
                map.put(s.charAt(j),j);
                j++;
                len=Math.max((j-i),len);
            }
        }
        return len;
    }
//    最长公共前缀----纵向比较
    public static String longestCommonPrefix(String[] strs){
        if (strs.length==0){
            return "";
        }
        if (strs.length==1){
            return strs[0];
        }
        for(int i=0;i<strs[0].length();i++){
            Character character=strs[0].charAt(i);
            for(String s:strs){
                if (i>=s.length()){
                    return strs[0].substring(0,i);
                }
                if (s.charAt(i)!=character){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
//    最大子序列
    public static int maxSubSequence(int[] data){
        int max=0;
        int currentMax=0;
        for(int i=0;i<data.length;i++){
            currentMax+=data[i];
            if(currentMax>0){
                max=Math.max(max,currentMax);
            }else{
                currentMax=0;
            }
        }
        return max;
    }
//    最长递增子序列长度
    public static int maxLengthIncrementSequence(int[] data){
        int maxLen=0;
        int[] dp=new int[data.length];
        for(int i=0;i<data.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if (data[j]<data[i]&&dp[j]+1>dp[i]){//为什么要加上dp[j+1]>dp[i]呢?
//                    不加的话如果最后的一个数最大,那么得到的结果就是整个数组的长度
//                    加了的话只有大于1的子序列的才能续上来
                    dp[i]=dp[i]+1;
                    maxLen=Math.max(maxLen,dp[i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return maxLen;
    }
    public static void DismaxLengthIncrementSequence(int[] data){//将最长递增子序列打印出来,通过保存快照
        int[] dp=new int[data.length];
        int[] snap=new int[data.length];
        int maxLen=0;
        for(int i=0;i<data.length;i++){
            dp[i]=1;
            int preMax=maxLen;
            int k=0;
            int[] tmp=new int[data.length];
            for(int j=0;j<i;j++){
                if (data[j]<data[i]&&dp[j]+1>dp[i]){
                    dp[i]+=1;
                    maxLen=Math.max(maxLen,dp[i]);
                    tmp[k++]=j;
                }
            }
            tmp[k]=i;
            if (maxLen>preMax){
                for(int m=0;m<tmp.length;m++){
                    snap[m]=tmp[m];
                }
            }
        }
        for(int i=0;i<maxLen;i++){
            System.out.println(data[snap[i]]);
        }
    }
}