package com.fcy.Algorithm;

public class DayTwo {
    public static void main(String[] args) {
        int array[]={1,5,2,7,3,8,4};
        SelectSort(array);
        for(int i:array){
            System.out.print(i);
        }
        System.out.println(BinnarySearch(array, 11, 0, array.length));
    }
//    插入排序 固定元素 每次和前面的元素比较 符合条件则交换位置
    public static void InsertSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(array[j]<array[j-1]){
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }else
                    break;
            }
        }
    }
//    选择排序 固定位置 每次把剩余元素最小的或最大的放在固定位置
    public static void SelectSort(int[] array){
        for(int i=0;i<array.length;i++){
            int index=i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[index])
                    index=j;
            }
            if(index!=i){
                int temp=array[i];
                array[i]=array[index];
                array[index]=temp;
            }
        }
    }
    public static void BubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]<array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }
    public static void QuickSort(int[] array,int start,int end){
        int low=start;
        int high=end;
        int key=array[start];
        while(low<high){
            while(low<high&&array[high]>=key)
                high--;
            if(array[high]<key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
            while(low<high&&array[low]<=key)
                low++;
            if(array[low]>key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
        }
        if(low!=start)QuickSort(array,start,low-1);
        if(high!=end)QuickSort(array,low+1,end);
    }
    public static int BinnarySearch(int[] array,int obj,int start,int end){
        if(start<end)
            return -1;
        int middle=(start+end)/2;
        if(array[middle]==obj){
            return middle;
        }else if(array[middle]<obj){
            return BinnarySearch(array,obj,middle+1,end);
        }else{
            return BinnarySearch(array,obj,start,middle-1);
        }
    }
}
