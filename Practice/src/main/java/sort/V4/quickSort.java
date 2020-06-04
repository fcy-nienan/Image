package sort.V4;/*
 * Author:fcy
 * Date:2020/2/15 16:33
 */

import sort.AbstractSort;
import sort.sortUtil;

public class quickSort extends AbstractSort {
    public static void main(String[] args) {
        new quickSort().arrayLen(1111).sortCount(2000).execute();
    }
    @Override
    protected void sort(int[] array) {
        quickSort(array,0,array.length-1);
    }
    public void quickSort(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        int oldkey=array[low];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            sortUtil.swap(array,high,low);
            while (low<high&&array[low]<=key){
                low++;
            }
            sortUtil.swap(array,high,low);
        }
//        下面两个if都不会输出
        if (low!=high) System.out.println(low+":"+high);//最后的low和high一定相等
        if (array[low]!=oldkey) System.out.println(array[low]+":"+oldkey);//最后的array[low]一定是那个key
//        如果start=4,low=5  这时是否可以不进行一次函数调用
//        不行,主要是还有另外一种情况,走完上面的while循环后low直接等于start了或者low直接等于end了,此时会栈溢出
//        但下面的方式时可以的,通过start!=low-1和end!=low+1避免一次函数调用
//        比如start=4,low=5,此时执行的函数时quickSort(array,4,4),该次函数调用while循环都进不去
        if (start!=low&&start!=low-1)quickSort(array,start,low-1);
        if (end!=low&&end!=low+1)quickSort(array,low+1,end);
    }
}
