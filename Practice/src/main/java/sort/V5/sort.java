package sort.V5;
/*
 * Author:fcy
 * Date:2020/2/19 13:26
 */
import sort.AbstractSort;
import sort.sortUtil;

import java.util.Arrays;

public class sort extends AbstractSort {
    public static int count=0;
    public static void main(String[] args) {
//        new sort().execute();
        int[] array = new int[]{1,2,3,4,8,1,0,3,9,5};
        new sort().sort(array);
        System.out.println(count);
    }
    @Override
    protected void sort(int[] array) {
        heapSort(array);
    }
    public static void quickSort(int[] array,int start,int end){
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
        if (end!=low)quickSort(array,low+1,end);
    }
    public static void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        System.out.println(Arrays.toString(array));
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,0,array.length-i);
            System.out.println(Arrays.toString(array));
        }
    }
    public static void adjustHeap(int[] array,int current,int len){
        for (int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i]<array[i+1]){
                i=i+1;
            }
            if (array[current]<array[i]){
                sortUtil.swap(array,i,current);
                current=i;
            }else{
                break;
            }
        }
    }
    public static void selectSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int minIndex=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,i,minIndex);
        }
    }
    public static void insertSort1(int[] array){
        for (int i=1;i<array.length;i++){
            int old=array[i];
            int j=i-1;
            while (j>=0&&old<array[j]){
                array[j+1]=array[j];
                j=j-1;
            }
            array[j+1]=old;
        }
    }
    public static void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    public static void merge(int[] array,int start,int mid,int end){
        int i=start,j=mid+1,k=0;
        int[] tmp=new int[end-start+1];
        while (i<=mid&&j<=end){
            if (array[i]<array[j]){
                tmp[k++]=array[i++];
            }else{
                tmp[k++]=array[j++];
            }
        }
        while (i<=mid){
            tmp[k++]=array[i++];
        }
        while (j<=end){
            tmp[k++]=array[j++];
        }
        k=0;
        while (start<=end){
            array[start++]=tmp[k++];
        }
    }
    public static void shellSort(int[] array){
        int gap=array.length;
        while (gap>0){
            for (int i=gap;i<array.length;i++){
                int tmp=array[i];
                int j=i-gap;
                count++;
                while (j>=0&&tmp<=array[j]){//这里等于需要格外注意
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=tmp;
            }
            gap=gap/2;
        }
    }
//    下面这种写法会造成死循环,当gap==0时while会死循环
//    但如果将while(j>=0&&old<=array[j])
//    变为while(j>=0&&old<array[j])则不会死循环
//    造成这样的原因在于gap等于0时的处理
//    上面那种情况gap=0不会进入循环
//    但下面这种情况当gap=1时gap>0,然后gap=gap>>1==0
//    此时gap==0,那么i=0,j=0,old和array[j]是同一个数
//    如果写了等于的话那么一直会死循环
//    如果不写等于的话就会退出了
//    另外下面这种方式还会多循环len次
    public static void shellSort1(int[] array){
        int gap=array.length;
        while (gap>0){
            gap=gap>>1;
            for (int i=gap;i<array.length;i++){
                int old=array[i];
                int j=i-gap;
                count++;
                while (j>=0&&old<=array[j]){
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=old;
            }
        }
    }
}
