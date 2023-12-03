package sort.V6;

import sort.AbstractSort;

public class mergeSort  extends AbstractSort {

    public static void main(String[] args) {
        new mergeSort().execute();
    }
    protected void sort(int[] array) {

    }
    public void mergeSort(int[] array,int start,int end,int[] temp){
        if (start < end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid,temp);
            mergeSort(array,mid+1,end,temp);
            merge(array,start,mid,end,temp);
        }
    }
    public void merge(int[] array,int start,int mid,int end,int[] temp){
        int i = start;
        int j = mid + 1;
        int k = 0;
        while(i<=mid && j<=end){
            if (array[i] <= array[j]){
                temp[k++] = array[j++];
            }else{
                temp[k++] = array[i++];
            }
        }
        while(i<=mid){
            temp[k++] = array[i++];
        }
        while(j<=end){
            temp[k++] = array[j++];
        }
        k = 0;
        while(start<=end){
            array[start++] = temp[k++];
        }
    }



}
