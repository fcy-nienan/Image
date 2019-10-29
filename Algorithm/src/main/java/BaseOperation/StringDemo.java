package com.fcy.Algorithm.BaseOperation;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-05  12:28
 */
public class StringDemo {
//    反转,7 3  8 4,奇数的中间一位也移动了
    public static char[] reverse(char[] s){
        for(int i=0;i<=(s.length-1)>>1;i++){
            char tmp=s[i];
            s[i]=s[s.length-i-1];
            s[s.length-i-1]=tmp;
        }
        return s;
    }
//    o(n*n)
    public static int[] productExceptSelf_ONN(int[] nums) {
        int[] value=new int[nums.length];
        for(int i=0;i<value.length;i++){
            int sum=1;
            for(int j=0;j<nums.length;j++){
                if (i!=j){
                    sum*=nums[j];
                }
            }
            value[i]=sum;
        }
        return value;
    }
//    o(n)
    public static int[] productExceptSelf_ON(int[] nums){
        int[] value=new int[nums.length];
        int sum=1;
        for(int i=0;i<nums.length;i++){
            sum*=nums[i];
        }
        for(int i=0;i<value.length;i++){

        }
        return value;
    }
    public static void main(String[] args) {
        int[] next=new int[]{1,2,3,4};
        int[] value=productExceptSelf_ON(next);
        System.out.println(Arrays.toString(value));
    }
}
