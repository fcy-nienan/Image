package com.fcy.Algorithm.LeetCode;

import java.net.Socket;
import java.util.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-07  1:16
 */
public class FindNoRepeatLength {
    public static int getLength(String s){
        int len=0;
        char[] chars=s.toCharArray();
        int[] result=new int[chars.length];
        Set<Character> set=new HashSet();
        for(int i=0;i<chars.length;i++) {
            if (!set.contains(chars[i]))
                set.add(chars[i]);
            else{
                result[i]=set.size();
                set.clear();
            }
        }
        for(int i=0;i<result.length;i++){

        }
        return set.size();
    }
    public static void quickSort(int[] data,int start,int end){
        int high=end,low=start;
        int key=data[(low+high)/2];
        while (low<high){
            while (low<high&&data[low]<=key){
                low++;
            }
            while (low<high&&data[high]>=key){
                high--;
            }
            if (low<high){
                int tmp=data[low];
                data[low]=data[high];
                data[high]=tmp;
            }
            System.out.println(Arrays.toString(data));
        }
        if (low==high){
            int tmp=key;
            data[start]=data[low];
            data[low]=tmp;
        }
        if (start!=low)quickSort(data,start,low-1);
        if (end!=high)quickSort(data,low+1,end);
    }

    public static void main(String[] args) {
//        int[] datas=new int[]{1,5,2,7,8,23,12,54,23};
//        quickSort(datas,0,datas.length-1);
//        System.out.println(Arrays.toString(datas));
//        String.format()


    }



}
