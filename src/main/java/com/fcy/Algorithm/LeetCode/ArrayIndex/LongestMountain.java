package com.fcy.Algorithm.LeetCode.ArrayIndex;

/**
 * @descripiton:
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 *
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 *
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * @author: fcy
 * @date: 2019-03-02  16:51
 */
public class LongestMountain {

    public static void main(String[] args) {
        System.out.println(longestMountain1(new int[]{2,1,4,7,3,2,5}));
        System.out.println(ms(new int[]{2,1,4,7,3,2,5}));
        System.out.println(optimization(new int[]{2,1,4,7,3,2,5}));
    }
    public static int longestMountain(int[] A) {
        int len=0;
        for(int i=0;i<A.length;i++){
            int j=i;
            while (j+1<A.length&&A[j+1]>A[j]) {
                j++;
            }
            int k=j;
            while (k+1<A.length&&A[k]>A[k+1]){
                k++;
            }
            if (k>j&&(k-j)>=1){
                len=Math.max(len,(k-i+1));
            }
        }
        return len;
    }
    public static int longestMountain1(int[] array){
        int count=0;
        int len=0;
        for (int i=0;i<array.length;i++){
            int j=i+1;
            while (j<array.length&&array[j]>array[j-1]) {
                j++;
                count++;
            }
            if (j==i+1)//如果上山没动,则直接开始下一次
                continue;
            int k=j;
            while (k<array.length&&array[k-1]>array[k]){
                count++;
                k++;
            }
            if (k>j&&j>i+1){
                count++;
                len=Math.max(len,k-i);
            }
        }
        System.out.println(count);
        return len;
    }
    public static int optimization(int[] array){
        int len=0;
        for(int i=0;i<array.length;i++){
            int j=i;
            while ((j+1)<array.length&&array[j]<array[j+1])
                j++;
            int k=j;
            while ((k+1)<array.length&&array[k]>array[k+1])
                k++;
            if (k>j&&j>i){
                len=Math.max(len,k-i+1);
            }
        }
        return len;
    }
    public static int ms(int[] A){
        int i = 0;
        int max = 0;
        int count=0;
        while (i < A.length - 1){
            int start = 0;
            int peek = 0;
            int len = 0;
            while (i < A.length - 1 && A[i] >= A[i + 1]){
                count++;
                i ++;
            }
            start = i;
            while (i < A.length - 1 && A[i] < A[i + 1]){
                count++;
                i ++;
            }
            peek = i;
            while (i < A.length - 1 && A[i] > A[i + 1]){
                count++;
                i ++;
            }
            len = i - start + 1;
            if (i > peek && peek > start){
                count++;
                max = max < len ? len : max;
            }
        }
        System.out.println(count);
        return max;
    }
}
