package com.fcy.Algorithm.Sort;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-26  13:31
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array=new int[]{8,4,1,9,2,6,5,4,2,7};
        int[] temp=new int[array.length];
        sort2(array,0,array.length-1,temp);
        System.out.println(Arrays.toString(temp));
    }
    public static void sort(int[] array,int start,int end,int[] temp){
        System.out.printf("%s %s !\r\n",start,end);
        if(start<end){
            int mid=(start+end)/2;
            sort(array,start,mid,temp);
            sort(array,mid+1,end,temp);
            merge(array,start,mid,end,temp);
        }
    }
//    将两个有序数组合并为一个有序数组
    public static void merge(int[] array,int start,int mid,int end,int[] temp){
//        System.out.printf("%s %s %s!\r\n",start,mid,end);
        int i=start,j=mid+1,t=0;
        while(i<=mid&&j<=end){
            if(array[i]<=array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        while(i<=mid){
            temp[t++]=array[i++];
        }
        while(j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        while(start<=end){
            array[start++]=temp[t++];
        }
    }

    public static void sort1(int[] array,int start,int end,int[] temp){
        if(start<end){
            int mid=(start+end)/2;
            sort(array,start,mid,temp);
            sort(array,mid+1,end,temp);
            merge1(array,start,mid,end,temp);
        }
    }
    public static void merge1(int[] array,int start,int mid,int end,int[] temp){
        int i=start,j=mid+1,t=0;
        while(i<=mid&&j<=end){
            if(array[i]<array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        while(i<=mid){
            temp[t++]=array[i++];
        }
        while(j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        while(t<=end){
            array[start++]=temp[t++];
        }
    }

    public static void sort2(int[] array,int start,int end,int[] temp){
        if (start<end){
            int mid=(start+end)/2;
            sort2(array,start,mid,temp);
            sort2(array,mid+1,end,temp);
            merge2(array,start,mid,end,temp);
        }
    }
    public static void merge2(int[] array,int start,int mid,int end,int[] temp){
        int i=start,j=mid+1,t=0;
        while(i<=mid&&j<=end){
            if (array[i]<=array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        while(i<=mid){
            temp[t++]=array[i++];
        }
        while(j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        System.out.println("before:"+Arrays.toString(array));
        while(start<=end){
            array[start++]=temp[t++];
        }
        System.out.println("after:"+Arrays.toString(array));
    }


}
