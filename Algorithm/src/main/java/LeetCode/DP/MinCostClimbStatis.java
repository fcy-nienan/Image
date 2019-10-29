package com.fcy.Algorithm.LeetCode.DP;

import java.util.Arrays;

/**
 * @descripiton:
 * 数组的每个索引做为一个阶梯，第i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *
 * 解法:假设给定的数组长度为n,我们需要创建一个长度为n+1的dp数组,第i项代表要走到第i阶梯最少需要花费多少体力
 * 则我们需要求的就是第n+1箱的值,第i项的值等于Min(dp[i-1]+cost[i],dp[i-2]+cost[i]);
 * @author: fcy
 * @date: 2019-03-08  0:07
 */
public class MinCostClimbStatis {
    public static void main(String[] args) {               //0 1   2 2 3 4   4 5
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        System.out.println(minCostClimbingStairs(new int[]{0,0,0,1}));
        System.out.println(minCostClimbingStairs(new int[]{0,0,1,1}));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp=new int[cost.length+1];
        dp[0]=cost[0];
        dp[1]=cost[1];
        for(int i=2;i<cost.length;i++){
            dp[i]=Math.min(dp[i-1]+cost[i],dp[i-2]+cost[i]);
        }
        System.out.println(Arrays.toString(dp));
        return Math.min(dp[cost.length-1],dp[cost.length-2]);
    }
}
