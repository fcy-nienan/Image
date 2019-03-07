package com.fcy.Algorithm.LeetCode.Struct;

import java.util.Arrays;
import java.util.Stack;

/**
 * @descripiton:最长有效括号
 * @author: fcy
 * @date: 2019-02-18  23:48
 */
public class Three {
    public static void main(String[] args) {
        String test1="(()";
        String test2=")()())";
        String test4="(((())))()()";
        String test3="))))(((())((((()())";
        System.out.println(longestValid(test4));
        System.out.println(dp(test4));
//        System.out.println(tenms(test1));
    }
//    如果是左括号,入栈,
//    如果是右括号
//            1:如果栈为空,说明没匹配到,将start设置为之后的一个位置
//            2:如果栈不为空,将其弹出,可以确定弹出的一定是一个左括号
//                    1:弹出后如果栈为空,取最大长度(这个start也是记录如果匹配失败的起始位置)
//                    2:弹出后如果栈不为空,将当前坐标减去栈顶元素然后取最大值
    public static int longestValid(String str){
        int res=0;
        int start=0;//匹配的起始位置
        Stack<Integer> st=new Stack<>();
        char[] s=str.toCharArray();
        for(int i=0;i<str.length();i++){
            if (s[i]=='('){
                st.push(i);
            }else{
                if (st.empty()){
                    start=i+1;
                }else {
                    st.pop();
                    if (st.empty()){
                        res=Math.max(res,i-start+1);
                    }else {
                        res = Math.max(res, i - st.peek());
                    }
                }
            }
        }
        return res;
    }
    public static int tenms(String s) {
        int maxnum = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxnum = Math.max(maxnum, 2 * left);
            } else if (left < right) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxnum = Math.max(maxnum, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        System.out.println(left+":"+right);
        return maxnum;
    }
    public static int ninems(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
    }
    private static int calc(char[] chars , int i ,  int flag,int end, char cTem){
        int max = 0, sum = 0, currLen = 0,validLen = 0;
        for (;i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen ++;
            if(sum < 0){
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            }else if(sum == 0){
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }
    public static int evelen_ms(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chas = s.toCharArray();
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            if (dp[i] > res)
                res = dp[i];
        }
        return res;
    }
    public static int dp(String s){
        int res=0;
        char[] chars=s.toCharArray();
        int[] dp=new int[chars.length];
        int pre=0;
        for(int i=1;i<chars.length;i++){
            if (chars[i]==')'){
                pre=i-dp[i-1]-1;//获取与之对应的上一个index
                if (pre>=0&&chars[pre]=='('){
                    dp[i]=dp[i-1]+2+(pre>0?dp[pre-1]:0);//为什么要加上dp[pre-1]?(())这种自然不需要,()()但这种需要
                }
            }
            if (dp[i]>res)
                res=dp[i];
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(Arrays.toString(dp));
        return res;
    }
    public static int twelve_ms(String s) {
        if (null == s){
            return 0;
        }

        int len = s.length(), max = 0;
        int d[] = new int[len];
        for(int i = len - 2; i >= 0; i--){
            int symIndex = i + 1 + d[i+1];
            if('(' == s.charAt(i) && symIndex < len && ')' == s.charAt(symIndex)){
                d[i] = 2 + d[i+1];
                if(symIndex+1 < len){
                    d[i] += d[symIndex+1];
                }
            }
            max = Math.max(max, d[i]);
        }

        return max;
    }
    public static int fiften_ms(String s) {
        String[] stash =new String[s.length()];
        int[] array = new int[s.length()];
        int j = 0;
        int index = -1;
        for (int i = 0; i < s.length(); i ++) {
            switch (s.substring(i, i + 1)) {
                case "(":
                    index++;
                    stash[index] = "(";
                    array[j++] = 0;
                    break;
                case ")":
                    if (index >= 0 && stash[index].equals("(")) {
                        array[j++] = 1;
                        index --;
                        int k = j - 2;
                        while (array[k] == 1) {
                            k --;
                        }
                        array[k] = 1;
                    } else {
                        array[j++] = 0;
                        index = -1;
                    }
                    break;
            }
        }
        int len = 0;
        int maxlen = 0;
        for (int i = j - 1; i >=0; i--){
            if (array[i] == 1) {
                len ++;
            } else {
                if (len > maxlen) {
                    maxlen = len;
                }
                len = 0;
            }
        }
        if (len > maxlen) {
            maxlen = len;
        }
        return maxlen;
    }
}
