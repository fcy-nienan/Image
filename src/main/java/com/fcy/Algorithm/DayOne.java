package com.fcy.Algorithm;

public class DayOne {
    public static void main(String[] args) {
        int[] array={1,8,2,2,6,4,9,4,8,4};
        shell_sort(array);
        for(int i:array){
            System.out.print(i);
        }
        System.out.println();
        System.out.println(BinarySearch(array, 1, 0, array.length));
    }
    public static void BubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>=array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }
    public static int index(int[] array,int obj,int start,int end){
        int middle=(start+end)/2;
        if(obj==array[middle])
            return middle+1;
        else if(obj>array[middle]){
            return index(array,obj,middle+1,end);
        }else{
            return index(array,obj,start,middle-1);
        }
    }
    public static int BinarySearch(int[] array,int obj,int start,int end){
        int middle=(start+end)/2;
        if(array[middle]==obj){
            return middle;
        }else if(array[middle]>obj){
            return BinarySearch(array,obj,start,middle-1);
        }else{
            return BinarySearch(array,obj,middle+1,end);
        }
    }
    public static void SelectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int index=i;
            for(int j=i;j<array.length;j++){
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
    public static void InsertSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(array[j]<array[j-1]){
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }else{
                    break;
                }
            }
        }
    }
    public static void ShellSort(int[] array){
        int incre=array.length;
        while(true){
            incre=incre/2;
            for(int i=0;i<incre;i++){
                for(int j=i+incre;j<array.length;j+=incre){
                    for(int k=j;k>i;k-=incre){
                        if(array[k]<array[k-incre]){
                            int temp=array[k-incre];
                            array[k-incre]=array[k];
                            array[k]=temp;
                        }else{
                            break;
                        }
                    }
                }
            }
            if(incre==1)
                break;
        }
    }
    public static void shell_sort(int array[]){
        int temp = 0;
        int incre = array.length;

        while(true){
            incre = incre/2;
            for(int k = 0;k<incre;k++){    //根据增量分为若干子序列
                for(int i=k+incre;i<array.length;i+=incre){
                    for(int j=i;j>k;j-=incre){
                        if(array[j]<array[j-incre]){
                            temp = array[j-incre];
                            array[j-incre] = array[j];
                            array[j] = temp;
                        }else{
                            break;
                        }
                    }
                }
            }
            if(incre == 1){
                break;
            }
        }
    }
    public static void QuickSort(int[] array,int start,int end){
        int low=start;
        int high=end;
        int key=array[low];
        while(low<high){
            while(low<high&&array[high]>=key)//这里必须有等号 没有等号的话最后一个数等于key的时候就走不动了
                high--;
            if(array[high]<key){
                int temp=array[high];
                array[high]=array[low];
                array[low]=temp;
            }
            while(low<high&&array[low]<=key)//
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
}
