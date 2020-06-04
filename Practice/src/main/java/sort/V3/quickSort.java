package sort.V3;/*
 * Author:fcy
 * Date:2020/2/8 21:07
 */

import sort.AbstractSort;
import sort.sortUtil;

public class quickSort extends AbstractSort {
    public static void main(String[] args) {
        new quickSort().arrayLen(12345).execute();
    }
    @Override
    protected void sort(int[] array) {
        quickSort(array,0,array.length-1);
    }
    public static void quickSort(int[] array,int start,int end){
        int low=start,high=end;
        int oldkey=array[low];
        int key=array[low];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
//            这种if可以不要
//            if (array[high]<key){
                sortUtil.swap(array,low,high);
//            }
            while (low<high&&array[low]<=key){
                low++;
            }
//            if (array[low]>key){
                sortUtil.swap(array,low,high);
//            }
        }
        if (low!=high) System.out.println(low+":"+high);//最后的low和high一定相等
        if (array[low]!=oldkey) System.out.println(array[low]+":"+oldkey);//最后的array[low]一定是那个key
        if (start!=low)quickSort(array,start,low-1);
        if (end!=high)quickSort(array,low+1,end);
    }
    public static void quickSort1(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            sortUtil.swap(array,low,high);
            while (low<high&&array[low]<=key){
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (start!=low)quickSort(array,start,low-1);
        if (end!=high)quickSort(array,low+1,end);
    }
}
