package com.fcy.Algorithm.Search;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  0:30
 */
public class Application {
    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
//                System.out.println(i);
//                System.out.println(arr[i]);
                ++i;
            }
            System.out.println(Arrays.toString(arr));
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }
    public static void main(String[] args) {
        int[] arr = new int[]{5,1,9,3,7,4,8,6,2};
        qSort(arr, 0, arr.length - 1);
        String out = "";
        for (int digit : arr) {
            out += (digit + ",");
        }
        System.out.println(out);
    }
}
