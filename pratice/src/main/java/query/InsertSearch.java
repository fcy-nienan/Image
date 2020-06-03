package query;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Objects;

public class InsertSearch {
    public static void main(String[] args) {

        FileOutputStream out;
        BufferedOutputStream outputStream;
        int arrayCount=3000;
        int[] array=new int[arrayCount];
        for(int i=0;i<array.length;i++){
            array[i]=i;
        }
        testOnce(array,100);
        BinarySearch.test1(array,100);
    }
    public static void testOnce(int[] array,int target){
        System.out.println(insertSearch(array,target));
    }
//    动态调整mid的位置,而不是死板的1/2
    public static boolean insertSearch(int[] array,int target){
        Objects.requireNonNull(array,"数组不能为空!");
        int low=0,high=array.length-1,count=0;
        while (low<=high){
            count++;
            int mid=low+(target-array[low])/(array[high]-array[low])*(high-low);
            if (array[mid]>target){
                high=mid-1;
            }else if (array[mid]<target){
                low=mid+1;
            }else{
                System.out.printf("%d \r\n",count);
                return true;
            }
        }
        return false;
    }
}
