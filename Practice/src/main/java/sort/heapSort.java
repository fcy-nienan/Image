package sort;

public class heapSort extends AbstractSort {

    public static void main(String args[]) {
        new heapSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        heapSort1(array);
    }
    private static void heapSort1(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,0,array.length-i);
        }
    }
    public static void adjustHeap(int[] array,int current,int len){
        for(int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i]<array[i+1]){
                i=i+1;
            }
            if (array[i]>array[current]){
                sortUtil.swap(array,current,i);
                current=i;
            }else{
                break;
            }
        }
    }
}
