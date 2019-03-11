package com.fcy.Algorithm.LeetCode.DP;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-10  22:15
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {
//        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0},{1,1},{0,0}}));
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        dp[0][0]=obstacleGrid[0][0]==1?0:1;
        for(int i=1;i<n;i++){
            dp[0][i]=obstacleGrid[0][i]==1?0:dp[0][i-1];
        }
        for(int i=1;i<m;i++){
            dp[i][0]=obstacleGrid[i][0]==1?0:dp[i-1][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=obstacleGrid[i][j]==1?0:dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
