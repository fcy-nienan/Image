package sort;

import java.util.Arrays;

public class quickSort extends AbstractSort {
    @Override
    protected void sort(int[] array) {
        q3(array,0,array.length-1);
    }
    public static void main(String args[]) {
        int[] arr1=new int[]{3, 3, 2, 1, 4};
        q3(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
        new quickSort().arrayLen(5).sortCount(1000000).execute();
    }
    //像数组[3, 0, 1, 1, 3]  两个地方都加了swap,那么只需要走一遍while循环
    //如果只是再最后加了swap,那么需要走两遍while循环
    //只有>=和<可以
    //只有low low+1可以
    //如果要逆序的话比较符号变为  <=和> 就行了
    //swap是一个还是两个无所谓
    public static void q3(int[] array,int start,int end){
        int key=array[start];
        int low=start,high=end;
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
//            sortUtil.swap(array,low,high);
            while (low<high&&array[low]<key){
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (low!=start)q3(array,start,low);
        if (high!=end)q3(array,low+1,end);
    }
}
