package sort;
//选择排序
//时间复杂度o(n*n) 空间复杂度o(1)  稳定性:是
public class selectSort extends AbstractSort {
    public static void main(String args[]) {
        new selectSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        selectSort2(array);
    }
    public static void selectSort2(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            int tmp=array[i];
            array[i]=array[minIndex];
            array[minIndex]=tmp;
        }
    }
    public static void selectSort3(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[j]>array[minIndex]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,minIndex,i);
        }
    }
    public static void insertSelect(int[] array){
        for(int i=1;i<array.length;i++){
            int value=array[i];
            int j=i-1;
            while (j>=0&&array[j]<array[i]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=value;
        }
    }
    public static void shellSort(int[] array){
        int gap=array.length;
        while (gap>=1){
            gap=gap/2;
            for(int i=gap;i<array.length;i++){
                int j=i-gap;
                int value=array[i];
                while (j>=0&&array[j]>value){
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=value;
            }
        }
    }
    public static void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            reduce(array,start,mid,end,new int[]{});
        }
    }
    public static void reduce(int[] array,int start,int mid,int end,int[] tmp){
        int i=start,j=mid+1,t=0;
        while (i<=mid&&j<=end){
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
    public static void headpSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,array.length,i);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,array.length-i,0);
        }
    }
    public static void adjustHeap(int[] array,int len,int current){
        for(int i=current*2+1;i<len;i=2*current+1){
            int value=array[current];
            if (i+1<len&&array[i]<array[i+1]){
                i++;
            }
            if (array[i]>value){
                sortUtil.swap(array,i,current);
                current=i;
            }else{
                break;
            }
        }
    }
}
