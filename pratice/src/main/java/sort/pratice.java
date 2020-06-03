package sort;

public class pratice extends AbstractSort {
    public static void main (String args[]) {
        new pratice().execute();
    }

    @Override
    protected void sort (int[] array) {
        heapSort(array);
    }

    public void bubble(int[] array){
        for (int i=0;i<array.length-1;i++){
            for (int j=1;j<array.length-i;i++){
                if (array[j]<array[j-1]){
                    sortUtil.swap(array,j,j-1);
                }
            }
        }
    }
    public void selectSort(int[] array){
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
    public void insertSort(int[] array){
        for (int i=1;i<array.length;i++){
            int old=array[i];
            int j=i-1;
            while (j>0&&old<array[j]){
                array[j+1]=array[j];
                j-=1;
            }
            array[j+1]=old;
        }
    }
    public void shellSort(int[] array){
        int gap=array.length;
        while (gap>0){
            gap=gap/2;
            for (int i=gap;i<array.length;i++){
                int old=array[i];
                int j=i-gap;
                while (j>=0&&old>array[j]){
                    array[j+gap]=array[j];
                    j-=gap;
                }
                array[j+gap]=old;
            }
        }
    }
    public void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end,new int[array.length]);
        }
    }
    public void merge(int[] array,int start,int mid,int end,int[] temp){
        int i=start,j=mid+1,t=0;
        while (i<=start&&j<=end){
            if (array[i]<array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        if (i<=start){
            temp[t++]=array[i++];
        }
        if (j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        while (start<=end){
            array[start++]=temp[t++];
        }
    }
    public void adjustHeap(int[] array,int current,int len){
        for (int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i+1]>array[i]){
                i++;
            }
            if (array[i]>array[current]){
                sortUtil.swap(array,i,current);
                current=i;
            }else{
                break;
            }
        }
    }
//    先调整为最大堆，然后调换堆顶元素和最后一个元素，然后再调整，再调换倒数第二个元素
//    刚开始需要调整每一棵子树为一个最大堆
//    然后调换位置，调换位置之后只是使得当前节点不是最大堆，所以只需要调整当前节点为最大堆就行
    public void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            if (i==array.length-1){
                break;
            }
            adjustHeap(array,0,array.length-i);

        }
    }

}
