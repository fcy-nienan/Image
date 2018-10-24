package com.fcy.Algorithm;

public class DayThree {
    public static void main(String[] args) {
        int[] array={1,8,2,2,6,4,9,4,8,4};
//        QuickSort(array,0,array.length-1);
//        BubbleSort(array);
//        SelectSort(array);
        InsertSort(array);
        for(int i:array){
            System.out.print(i);
        }
        System.out.println();
        System.out.println(BinarySearch(array,99,0,array.length));
    }
    public static int BinarySearch(int[] array,int obj,int start,int end){
        if(start<end){
            int middle=(start+end)/2;
            if(array[middle]==obj){
                return middle;
            }else if(array[middle]>obj){
                return BinarySearch(array,obj,start,middle-1);
            }else{
                return BinarySearch(array,obj,middle+1,end);
            }
        }else{
            return -1;
        }
    }
    public static void QuickSort(int[] array,int start,int end){
        int low=start;
        int high=end;
        int key=array[low];
        while(low<high){
            while(low<high&&array[high]>=key)
                high--;
            if(array[high]<key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
            while(low<high&&array[low]<=key)//这步就不一定要用等号了 但加上好点
                low++;
            if(array[low]>key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
        }
        if(start!=low)QuickSort(array,start,low-1);
        if(end!=high)QuickSort(array,low+1,end);
    }
    public static void BubbleSort(int[] array){
        for(int i=0;i<array.length-1;i++){//array.length-1轮 最后一轮不用
            for(int j=array.length-1;j>i;j--){//array.length-i-1次交换 i=3的时候 剩余7个元素 进行7-1次交换(10-i-1)
                if(array[j]>array[j-1]){
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }
            }
        }
    }
    public static void SelectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min=i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]<array[j])
                    min=j;
            }
            if(min!=i){
                int temp=array[i];
                array[i]=array[min];
                array[min]=temp;
            }
        }
    }
    public static void InsertSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(array[j]>array[j-1]){
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }
            }
        }
    }
}
