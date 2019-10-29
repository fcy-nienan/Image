package com.fcy.Algorithm.LeetCode.DP;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-11  1:33
 */
public class MakeMoney {
    public static void main(String[] args) {
        makeMoney(new int[]{1,2,5,10},10);
    }
    public static void makeMoney(int[] coins,int money){
        int[] arrays=new int[money+1];
        arrays[0]=0;
        int i=1;
        for(;i<=money;i++){
            int minMoney=i;
            for(int j=0;j<coins.length;j++){
                if (i>=coins[j]){
                    int tmp=arrays[i-coins[j]]+1;
                    if (tmp<minMoney){
                        minMoney=tmp;
                    }
                }
            }
            arrays[i]=minMoney;
        }
        System.out.println(Arrays.toString(arrays));
        System.out.println("number:"+arrays[i-1]);
    }
}
