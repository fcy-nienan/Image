package sort.V4;/*
 * Author:fcy
 * Date:2020/2/15 17:57
 */

import sort.AbstractSort;
import sort.sortUtil;

public class heapSort extends AbstractSort {
    public static void main(String[] args) {
        new heapSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        heapSort(array);
    }
    public void adjustHeap(int[] array,int current,int len){
        for (int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i]>array[i+1]){
                i=i+1;
            }
            if (array[current]>array[i]){
                sortUtil.swap(array,current,i);
                current=i;
            }else{
                break;
            }
        }
    }
    public void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,0,array.length-i);
        }
    }
}
