package sort.V6;

import sort.AbstractSort;
import sort.sortUtil;

public class heapSort extends AbstractSort {
    public static void main(String[] args) {
        new heapSort().execute();
    }

    public void sort(int[] array){
        heapSort(array);
    }
    public void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length - i);
            adjustHeap(array,0,array.length - i);
        }
    }
    public void adjustHeap(int[] array,int start,int end){
        for(int i=start*2+1;i<end;i=i*2+1){
            if (i+1<end && array[i] < array[i+1]){
                i++;
            }
            if (array[start] < array[i]){
                sortUtil.swap(array,start,i);
            }
            start = i;
        }
    }


    public void adjustHeap1(int[] array,int start,int end){
        for (int i=start * 2 + 1;i<end;i=i*2+1){
            if (i+1<end && array[i] < array[i+1]){
                i++;
            }
            if (array[start] < array[i]){
                sortUtil.swap(array,i,start);
                start = i;
            }else{
                break;
            }
        }
    }
    public void heapSort1(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap1(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length - i);
            adjustHeap1(array,0,array.length - i);
        }
    }






}
