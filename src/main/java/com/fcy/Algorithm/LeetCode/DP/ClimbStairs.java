package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-04  23:17
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(11));
    }
    public static int climbStairs(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
