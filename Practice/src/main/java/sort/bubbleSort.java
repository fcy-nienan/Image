package sort;

//冒泡排序    时间复杂度o(n*n)   空间复杂度o(1)
//每次循环找出一个最小或者最大的数一直上浮或者下降到一端
//是一种稳定性的排序
public class bubbleSort extends AbstractSort {
    public static void main(String args[]) {
        new bubbleSort()
                .execute();
    }
    protected void sort(int[] array) {
        heapSort(array);
    }

    public static void bubbleSort2(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if (array[j]<array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
        }
    }
    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1;j++){
                if (array[j]>array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
        }
    }
    public void bubbleSort1(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=1;j<array.length-i;j++){
                if (array[j-1]>array[j]){
                    sortUtil.swap(array,j,j-1);
                }
            }
        }
    }
    public void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,minIndex,i);
        }
    }
    public void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i-1;
            int value=array[i];
            while (j>=0&&value>array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=value;
        }
    }
    public void merge(int[] array){
        mergeSort(array,0,array.length-1,new int[array.length]);
    }
    public void mergeSort(int[] array,int start,int end,int[] tmp){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid,tmp);
            mergeSort(array,mid+1,end,tmp);
            merge(array,start,mid,end,tmp);
        }
    }
    private void merge(int[] array,int start,int mid,int end,int[] tmp){
        int i=start,j=mid+1,t=0;
        while (i <= mid && j <= end){
            if (array[i]<array[j]){
                tmp[t++]=array[i++];
            }else{
                tmp[t++]=array[j++];
            }
        }
        while (i<=mid){
            tmp[t++]=array[i++];
        }
        while (j<=end){
            tmp[t++]=array[j++];
        }
        t=0;
        while (start<=end){
            array[start++]=tmp[t++];
        }
    }
    private void shellSort(int[] array){
        int gap=array.length;
        while (gap>=1){
            gap=gap/2;
            for(int i=gap;i<array.length;i++){
                int j=i-gap;
                int value=array[i];
                while (j>=0&&value>array[j]){
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=value;
            }
        }
    }
    public void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for(int i=1;i<array.length;i++){
            int max=array.length-i;
            sortUtil.swap(array,0,max);//此时堆顶的元素是最大或者最小,将其放在数组的最后-i位
            adjustHeap(array,0,max);
        }
    }
    private void adjustHeap(int[] array,int current,int len){
        for(int i=current*2+1;i<len;i=current*2+1) {
            if (i + 1 < len && array[i] < array[i + 1]) {
                i = i + 1;
            }
            if (array[current] < array[i]) {
                sortUtil.swap(array, current, i);
                current=i;
            }else{
                break;
            }
        }
    }
}
