package com.fcy.Algorithm.Search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arrays={2,5,7,1,9,5,6,4,10,19,13,26,14};
        shell_sort(arrays,arrays.length);
        for(int i:arrays){
            System.out.print(" "+i);
        }
    }
//    归并排序
    static void MergeSort(int[] array,int start,int end){//start和end都是下标0,length-1
        if(start<end){
            int middle=(start+end)/2;
            MergeSort(array,start,middle);
            MergeSort(array,middle+1,end);
            MemeryOneArray(array,start,middle,end,new int[10]);
        }
    }
    static void MemeryOneArray(int[] a,int start,int middle,int end,int[] temp){
        int i=0,j=0,k=0;
        i=start;
        j=middle+1;
        while(true){
            if(a[i]<=a[j]){

            }
        }
    }
//    将两个有序数组合并为一个有序数组
    static void MemeryArray(int[] a,int[] b,int[] c){
        int i=0,j=0,k=0;
        while(i<a.length&&j<b.length){
            if(a[i]<=b[j]){
                c[k]=a[i];
                i++;
            }else{
                c[k]=b[j];
                j++;
            }
            k++;
        }
        while(i<a.length){
            c[k++]=a[i++];
        }
        while(j<b.length){
            c[k++]=b[j++];
        }
    }
    static void QuickSort(int[] array,int start,int end){
        int low=start;
        int high=end;
        int key=array[start];
        while(low<high){
            while(low<high&&array[high]>=key){
                high--;
            }
            if(array[high]<key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
            while(low<high&&array[low]<=key){
                low++;
            }
            if(array[low]>key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
        }
        if(start!=low)QuickSort(array,start,low-1);
        if(end!=high)QuickSort(array,low+1,end);
    }
//    快排    改进:key的选取
    static void QuickSort1(int[] array,int start,int end){
//        start和end两个下标后面要用
        int low=start;
        int high=end;
        int key=array[start];//key=array[low]也可以 这个时候low的值还没变
        while(low<high){
            while(low<high&&array[high]>=key)
                high--;
            if(array[high]<key){//这里不需要= 因为前面的while判断了等于
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
            while(low<high&&array[low]<=key)//在while中加等于 要不然这个low不会走动 这个=不能去掉
                low++;
            if(array[low]>key){
                int temp=array[low];
                array[low]=array[high];
                array[high]=temp;
            }
        }
        if(start!=low)QuickSort1(array,start,low-1);
        if(end!=high)QuickSort1(array,low+1,end);
    }
    static void ShellInsert(int[] pDataArray, int d, int iDataNum)
    {
        for (int i = d; i < iDataNum; i += d)    //从第2个数据开始插入
        {
            int j = i - d;
            int temp = pDataArray[i];    //记录要插入的数据
            while (j >= 0 && pDataArray[j] > temp)    //从后向前，找到比其小的数的位置
            {
                pDataArray[j+d] = pDataArray[j];    //向后挪动
                j -= d;
            }

            if (j != i - d)    //存在比其小的数
                pDataArray[j+d] = temp;
        }
    }
    static void ShellSort(int[] pDataArray, int iDataNum)
    {
        int d = iDataNum / 2;    //初始增量设为数组长度的一半
        while(d >= 1)
        {
            ShellInsert(pDataArray, d, iDataNum);
            d = d / 2;    //每次增量变为上次的二分之一
        }
    }
    //    希尔排序  插入排序的一种变种  插入排序每次都值增加1  希尔排序则每次增加一个增量
    public static void ShellSort(int array[]){
        while(true){
            int incre=array.length/2;
            for(int i=0;i<incre;i++){//第一次for循环 0到增量
                for(int j=i+incre;j<array.length;j++){//第二层for循环 每个i+增量
                    for(int m=j;m>i;m-=incre){//第三次for循环每个j与之前的m比较
                        if(array[m]<array[i]){
                            int temp=array[m];
                            array[m]=array[i];
                            array[i]=temp;
                        }else{
                            break;
                        }
                    }
                }
            }
            if(incre==1){
                break;
            }
        }
    }
    public static void InsertSort1(int array[]){
        for(int i=1;i<array.length;i++){
            for(int j=i;j>0;j--){
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
    public static void shell_sort(int array[],int lenth){
        int temp = 0;
        int incre = lenth;
        while(true){
            incre = incre/2;
            for(int k = 0;k<incre;k++){    //根据增量分为若干子序列
                for(int i=k+incre;i<lenth;i+=incre){
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
//    插入排序  时间复杂度O(n^2)  如果数据序列基本有序，使用插入排序会更加高效
    public static void InsertSort(int[] array){
        for(int i=0;i<array.length-1;i++){//array.length-1  因为下面有了j=i+1
            for(int j=i+1;j>0;j--){
                if(array[j]<array[j-1]){
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }else{//如果不小于则不需要比较了   因为前面的都已经排好序了
                    break;
                }
            }
        }
    }
//    选择排序  时间复杂度O(n^2) 两个for循环
    public static void SelectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){//在剩下的元素中查找最小的元素并记录下标
                if(array[j]<array[minIndex])
                    minIndex=j;
            }
            if(minIndex!=i){//循环完毕后和第i个元素交换位置
                int temp=array[minIndex];
                array[minIndex]=array[i];
                array[i]=temp;
            }
        }
    }
//    冒泡排序  每进行一次循环都会把最大的或者最小的放在最左边或最右边
    public static void BubbleSort(int []array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]<array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            for(int m:array){
                System.out.print(" "+m);
            }
            System.out.println();
        }
    }
//    二分查找  需要一个有序的列表 并且这个有序是升序还是降序影响到下面的代码
    public static int index(int[] array,int obj,int start,int end){
        int mid=(start+end)/2;
        if(array[mid]==obj)//如果相对说明找到了
            return mid+1;
        else if(array[mid]<obj)//如果中间的小于要查找的值 说明??
            return index(array,obj,mid+1,end);
        else
            return index(array,obj,start,mid-1);

    }
}
