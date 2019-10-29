package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-04  23:39
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
    public static int maxSubArray(int[] nums) {
        int max=Integer.MIN_VALUE;
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i]=Math.max(nums[i],(dp[i-1]+nums[i]));
        }
        for(int i=0;i<dp.length;i++){
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}
