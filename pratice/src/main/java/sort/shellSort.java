package sort;

public class shellSort extends AbstractSort {
    public static void main(String args[]) {
        new shellSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        shellSort(array);
    }
    public static void shell(int[] array){
        for(int gap=array.length/2;gap>0;gap=gap/2){
            for (int j=0;j<gap;j++){
                for (int k=j+gap;k<array.length;k=k+gap){
                    int value=array[k];
                    int m=k-gap;
                    while (m>=0&&array[m]>value){
                        array[m+gap]=array[m];
                        m=m-gap;
                    }
                    array[m+gap]=value;
                }
            }
        }
    }
    public static void shellSort(int[] array){
        int gap=array.length;
        while (gap>=1){
            gap=gap/2;
            for(int i=gap;i<array.length;i++){
                int value=array[i];
                int j=i-gap;
                while (j>=0&&array[j]>value){
                    array[j+gap]=array[j];
                    j=j-gap;
                }
                array[j+gap]=value;
            }
        }
    }
    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i-1;
            int value=array[i];
            while (j>=0&&value<array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=value;
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
            sortUtil.swap(array,minIndex,i);

        }
    }
    public static void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end,new int[0]);
        }
    }
    public static void merge(int[] array,int start,int mid,int end,int[] temp){
        int i=start,j=mid+1,t=0;
        while (i<=mid&&j<=end){
            if (array[i]<array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        while (i<=mid){
            temp[t++]=array[i++];
        }
        while (j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        while (start<=end){
            array[start++]=temp[t++];
        }
    }
    public void adjustHead(int[] array,int len,int current){
        for (int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i]<array[i+1]){
                i++;
            }
            if (array[current]<array[i]){
                sortUtil.swap(array,i,current);
                current=i;
            }else{
                break;
            }
        }
    }
    public void heapSort(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHead(array,array.length,i);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHead(array,array.length-i,0);
        }
    }
}
