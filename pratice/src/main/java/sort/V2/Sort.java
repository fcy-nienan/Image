package sort.V2;
import sort.AbstractSort;
import sort.sortUtil;

import java.util.PriorityQueue;

public class Sort extends AbstractSort {
    @Override
    protected void sort (int[] array) {
        heapSort(array);
    }

    public void bubbleSort(int[] array){
        for (int i=0;i<array.length-1;i++){//9个数字排序,8个已经有序了,另一个不用排了
            for (int j=0;j<array.length-i-1;j++){//三个数组需要比较两趟,所以需要减一
                if (array[j]>array[j+1]){
                    sortUtil.swap(array,j,j+1);
                }
            }
        }
    }
    public void selectSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int minIndex=i;
            /*
            * 不应该是j<array.length-i,而是j<array.length
            * */
            //for(int j=i+1;j<array.length-i;j++){
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,i,minIndex);
        }
    }
    /*
    * 2,6,3,5,1,8,9
    *
    * 拿起一个3,然后假设第三个位置是空的,然后向前遍历并比较值决定用哪个槽位
    * 0-i是有序的,但是可能是这种的:0,3,6,9,2
    * 此时2需要放在0和3之间,所以是>=0的临界条件
    * 最后将空出来的槽位赋值给j=i+1这个元素的值
    * */
    public void insertSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int j=i+1;
            int tmp=array[j];
            while (j-1>=0&&tmp<array[j-1]){
                array[j]=array[j-1];
                j=j-1;
            }
            array[j]=tmp;
        }
    }
    public void mergeSort(int[] array,int start,int end){
        while (start<end){
            int mid=(start+end)/2;
            mergeSort(array,0,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    public void merge(int[] array,int start,int mid,int end){
        int[] tmp=new int[end-start+1];
        int k=0,i=start,j=mid+1;
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
    public void shellSort(int[] array){
        int gap=array.length;
        while (gap>0){
            gap=gap/2;
            for (int i=gap;i<array.length;i++){
                int j=i-gap;
                int tmp=array[i];
                while (j>=0&&array[j]>tmp){
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=tmp;
            }
        }
    }
    public void shellSort1(int[] array){
        int gap=array.length;
        while (gap>0){
            gap=gap/2;
            for (int i=0;i<gap;i++){
                for (int k=i+gap;k<array.length;k+=gap){
                    int j=k;
                    int tmp=array[j];
                    while (j-gap>=0&&array[j-gap]>tmp){
                        array[j]=array[j-gap];
                        j=j-gap;
                    }
                    array[j]=tmp;
                }
            }
        }
    }
    public static void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            for (int j=i*2+1;j<array.length;j=j*2+1){
//                if (j+1<array.length&&)
            }
        }
        for (int i=1;i<array.length-1;i++){
            sortUtil.swap(array,0,array.length-i);
            int j=0;
            while (j<=array.length/2-1){
                int left=j*2+1;
                int right=left+1;
                int current=left;
                if (right>array.length&&array[right]>array[left]){
                    current=right;
                }
                if (array[current]>array[j]){
                    sortUtil.swap(array,j,current);
                    j=current;
                }else{
                    break;
                }
            }
        }
    }
    public static void main (String args[]) {
        new Sort().execute();
        PriorityQueue queue;
    }
}
