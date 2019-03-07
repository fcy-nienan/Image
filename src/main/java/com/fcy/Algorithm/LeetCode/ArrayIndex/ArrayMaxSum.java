package com.fcy.Algorithm.LeetCode.ArrayIndex;

/**
 * @descripiton:    查找一个数组中连续子数组的最大值
 * m1:  暴力方法    两次for循环
 * m2:  动态规划    一个for循环     current next ,比较三个数,前面的连续最大值,下一个值,连续最大值和下一个值的和
 * @author: fcy
 * @date: 2018-12-09  22:20
 */
public class ArrayMaxSum {
    public static void main(String[] args) {
        int[] array=new int[]{-2, -1, -3, -8, 10, -3, 5, -6};
        System.out.println(getMaxSumDP(array));
    }
    public static int getMaxSumDP(int[] arrsy){
        int current=arrsy[0];
        int sum=arrsy[0];
        for(int i=0;i<arrsy.length;i++){
            if (current<0)
                current=0;
            current+=arrsy[i];
            sum=Math.max(sum,current);
        }
        return sum;
    }
}
