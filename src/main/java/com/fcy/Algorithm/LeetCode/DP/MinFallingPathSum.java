package com.fcy.Algorithm.LeetCode.DP;

/**
 * @descripiton:
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列
 *
 * 示例：
 * 输入：[[1,2,3]
 *      ,[4,5,6]
 *      ,[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12
 * @author: fcy
 * @date: 2019-03-09  20:40
 */
public class MinFallingPathSum {
    public static void main(String[] args) {
        System.out.println(minFallingPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
    public static int minFallingPathSum(int[][] A){
        int min=Integer.MAX_VALUE;
        int row=A.length,col=A[0].length;
        int[][] dp=new int[row][col];
        for(int i=0;i<col;i++){
            dp[0][i]=A[0][i];
        }
        for(int i=1;i<row;i++){
            for(int j=0;j<col;j++){
                if (j==0)
                    dp[i][j]=Math.min(dp[i-1][j]+A[i][j],dp[i-1][j+1]+A[i][j]);
                else if (j==col-1)
                    dp[i][j]=Math.min(dp[i-1][j]+A[i][j],dp[i-1][j-1]+A[i][j]);
                else
                    dp[i][j]=Math.min(Math.min(dp[i-1][j]+A[i][j],dp[i-1][j-1]+A[i][j]),dp[i-1][j+1]+A[i][j]);
            }
        }
        for(int i=0;i<col;i++){
            min=Math.min(min,dp[row-1][i]);
        }
        return min;
    }
}
