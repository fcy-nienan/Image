package sort;
//归并排序
public class mergeSort extends AbstractSort {
    public static void main(String args[]) {
        new mergeSort().execute();
    }

    @Override
    protected void sort(int[] array) {//需要注意的地方,这个是len-1
        mergeSort(array,0,array.length-1,new int[array.length]);
    }
    public static void mergeSort(int[] array,int start,int end,int[] tmp){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid,tmp);
            mergeSort(array,mid+1,end,tmp);
            merge(array,start,mid,end,tmp);
        }
    }
    public static void merge(int[] array,int start,int mid,int end,int[] tmp){
//        这里的j需要mid+1
        int t=0,i=start,j=mid+1;
        while (i<=mid&&j<=end){
            if (array[i]<array[j]){
                tmp[t++]=array[i++];
            }else{
                tmp[t++]=array[j++];
            }
        }
//        将两个数组中剩余的元素挪到临时数组
        while (i<=mid){
            tmp[t++]=array[i++];
        }
        while (j<=end){
            tmp[t++]=array[j++];
        }
//      将排好序的元素复制到原数组
        t=0;
        while (start<=end){
            array[start++]=tmp[t++];
        }
    }
    public static void adjustHeap(int[] array,int current,int len){
        for(int min=current*2+1;min<len;min=min*2+1){
            if (min+1<len&&array[min]>array[min+1]){
                min=min+1;
            }
            if (array[min]<array[current]){
                sortUtil.swap(array,min,current);
                current=min;
            }else{
                break;
            }
        }
    }
    public static void heapSort(int[] array){
        for(int i=array.length/2-1;i>=0;i--){//保证每个节点都符合堆的性质
            adjustHeap(array,i,array.length);
        }
        for(int i=1;i<array.length;i++){//将堆顶的元素和数组的最后一个元素交换,然后重新调整堆
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,i,array.length-i);
        }
    }
    public static void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[minIndex]>array[j]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,i,minIndex);
        }
    }
    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i-1;
            while (j>=0&&array[j]<array[i]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=array[i];
        }
    }
    public static void shellSort(int[] array){
        for (int gap=array.length/2;gap>0;gap=gap/2){

        }
    }
    public static void insertSortSingle(int[] array,int d,int index){
        int j=index-d;
        int insertValue=array[index];
        while (j>=0&&array[j]<array[index]){
            array[index]=array[j];
            j-=d;
        }
        array[j+d]=insertValue;
    }

    public void mergeSort1(int[] array){

    }
    public void merge(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            merge(array,start,mid);
            merge(array,mid+1,end);
            sort1(array,start,mid,end);
        }
    }
    public void sort1(int[] array,int start,int mid,int end){
        int i=start,j=mid+1,t=0;
        int[] tmp=new int[array.length];
        while (i<=start&&j<=end){
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

}
