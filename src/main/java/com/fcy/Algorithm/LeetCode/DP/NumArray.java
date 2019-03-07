package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-06  22:22
 */
public class NumArray {
    private int[] sums;
    public NumArray(int[] nums){
        sums[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            sums[i]=sums[i-1]+nums[i];
        }
    }
    public int sumRange(int i, int j) {
        return sums[j]-sums[i];
    }
}
