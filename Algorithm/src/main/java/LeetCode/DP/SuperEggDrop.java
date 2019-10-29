package com.fcy.Algorithm.LeetCode.DP;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @descripiton:
 * hashcode永远返回1会怎样:hash冲突
 *
 * @author: fcy
 * @date: 2019-03-12  1:22
 */
public class SuperEggDrop {
    private static Object object;
    private Object getObject(){
        if (object==null){
            synchronized (object.getClass()){
                if (object==null){
                    object=new Object();
                }
            }
        }
        return object;
    }
    public static void reportNum(int n){
        boolean[] booleans=new boolean[n+1];
        int count=1;
        int index=1;
        int sum=0;
        while (true){
            if (index>n){
                index=1;
            }
            if (booleans[index]){
                index++;
            }else {
                if (count % 3 == 0) {
                    booleans[index] = true;
                    sum++;
                }
                index++;
                count++;
            }
            if ((n-sum)==2)break;
        }
        System.out.println(Arrays.toString(booleans));
    }
    public static void main(String[] args) {
        reportNum(5);
    }
    private static int superEggDrop(int K,int N){
        int max=0;

        return max;
    }
}
