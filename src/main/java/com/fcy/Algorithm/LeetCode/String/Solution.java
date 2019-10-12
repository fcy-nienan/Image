package com.fcy.Algorithm.LeetCode.String;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-23  5:26
 * 123
 * 123%10=3  123/10=12
 * 12%10=2  12/10=1
 * 1%10=1 1/10=0;
 */
public class Solution {
//  判断一个整型是否溢出,用一个long类型来装,然后判断是否大于整型最大值或者最小值
//    整数溢出通过取余和相除
//    符号为反转通过与int类型的最大值01111111111111111111111111111111=Integer.MAX_VALUE
    public static int reverse(int x) {
        int i=-1;
        long sum=0;
        while(x!=0){
            i=x%10;
            sum+=i;
            sum*=10;
            x=x/10;
        }
        sum=sum/10;
        if (sum>Integer.MAX_VALUE||sum<Integer.MIN_VALUE)return 0;
        int res=(int)sum;
        if (x<0){
            res=res&Integer.MAX_VALUE;
        }
        return  res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
        long i=10;
        System.out.println(i<<32);
    }
}
