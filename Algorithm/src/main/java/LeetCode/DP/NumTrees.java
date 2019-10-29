package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * @author: fcy
 * @date: 2019-03-26  23:48
 */
public class NumTrees {
    public int numTrees(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;dp[2]=2;
        for(int i=3;i<=n;i++){

        }
        return 0;
    }
}
