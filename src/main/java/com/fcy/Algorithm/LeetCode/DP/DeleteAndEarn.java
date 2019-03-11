package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 *
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * nums的长度最大为20000。
 * 每个整数nums[i]的大小都在[1, 10000]范围内
 * @author: fcy
 * @date: 2019-03-09  19:00
 */
public class DeleteAndEarn {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,3,3,3,4}));
        System.out.println(optimization(new int[]{3,4,2}));
        System.out.println(asRob(new int[]{3,4,2}));
    }
    public static int deleteAndEarn(int[] nums) {
        if (nums.length==0)return 0;
        int max=0;
        int maxValue=0;
        int[] numCount=new int[10000];//numCount[i]存储值等于i的数有多少个
        for(int i=0;i<nums.length;i++){
            numCount[nums[i]]++;
            maxValue=Math.max(maxValue,nums[i]);
        }
        int[] dp=new int[maxValue+1];
        dp[0]=0;
        dp[1]=numCount[1];
        max=Math.max(dp[0],dp[1]);
        for(int i=2;i<=maxValue;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+i*numCount[i]);
            max=Math.max(max,dp[i]);
        }
        return max;
    }
    public static int  optimization(int[] nums){
        if (nums.length==0)return 0;
        int[] numCount=new int[10001];
        int max=0;//数组中最大的值
        for(int i=0;i<nums.length;i++){
            numCount[nums[i]]++;
            max=Math.max(max,nums[i]);
        }
        int t1=0,t2=numCount[1];
        for(int i=2;i<=max;i++){
            int tmp=Math.max(t2,t1+numCount[i]*i);
            t1=t2;
            t2=tmp;
        }
        return t2;
    }
    public static int asRob(int[] nums){//模仿打家劫舍
        int max=0;
        for(int i=0;i<nums.length;i++){
            max=Math.max(max,nums[i]);
        }
        int[] numCount=new int[max+1];
        for(int i=0;i<nums.length;i++){
            numCount[nums[i]]++;
        }
        int[] dp=new int[max+1];
        dp[0]=0;
        dp[1]=numCount[1];
        for(int i=2;i<=max;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+numCount[i]*i);
        }
        return dp[max];
    }
}
