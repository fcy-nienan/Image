package com.fcy.Algorithm.Sort;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-09  12:40
 */
public class MergeSort1 {
    public static void main(String[] args) {
        int[] array=new int[]{3,8,1,2,9,6,8,3};
        sort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
    public static void sort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            sort(array,start,mid);
            sort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    public static void merge(int[] array,int start,int mid,int end){
        int[] tmp=new int[end-start+1];
        int k=0;
        int i=start,j=mid+1;
        while(i<=mid&&j<=end){
            if (array[i]<array[j]){
                tmp[k++]=array[i++];
            }else{
                tmp[k++]=array[j++];
            }
        }
        while(i<=mid){
            tmp[k++]=array[i++];
        }
        while(j<=end){
            tmp[k++]=array[j++];
        }
        k=0;
        while(start<=end){
            array[start++]=tmp[k++];
        }
    }
}
