package com.fcy.Algorithm.Sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-09  16:47
 */
public class QUickSort1 {
    public static void main(String[] args) {
        int i=10,j=10;
        Random random=new Random();
        int[][] data=new int[i][j];
        for(int m=0;m<i;m++){
            for(int n=0;n<j;n++){
                data[m][n]=random.nextInt(100);
            }
        }
        for(int k=0;k<i;k++){
            quickSort1(data[k],0,data[k].length-1);
            System.out.println(Arrays.toString(data[k]));
        }
    }
    public static void quickSort2(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        while(low<high){
            while(low<high&&key<=array[high]){
                high--;
            }
            while(low<high&&key>=array[high]){
                low++;
            }
            swqp(array,low,high);
        }
        swqp(array,start,low);
        if (start!=low)quickSort2(array,start,low-1);
        if (end!=high)quickSort2(array,low+1,end);
    }
    public static void quickSort1(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[start];
        while(low<high){
            while(low<high&&key<=array[high]){
                high--;
            }
//            if (low<high){
                swqp(array,low,high);
//            }
            while(low<high&&key>=array[low]){
                low++;
            }
//            if (low<high){
                swqp(array,low,high);
//            }
        }
        if (start!=low){
            quickSort1(array,start,low-1);
        }
        if (end!=high)quickSort1(array,low+1,end);
    }
//    交换值
    public static void swqp(int[] array,int i,int j){
        if (i==j)return;
        array[i]=array[i]^array[j];
        array[j]=array[i]^array[j];
        array[i]=array[i]^array[j];
    }
}
