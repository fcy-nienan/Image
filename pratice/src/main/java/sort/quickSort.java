package sort;

import java.util.Arrays;

public class quickSort extends AbstractSort {
    @Override
    protected void sort(int[] array) {
        quickSort2(array,0,array.length-1);
    }
    public static void main(String args[]) {
        int[] arr1=new int[]{2,7,1,9,4,4,2};
//        int[] arr=new int[]{2,7,1,9,4,4,3};
        quickSort3(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
//        new quickSort().arrayLen(10000).sortCount(1000).execute();
    }
    public static void quickSort(int[] array,int start,int end){
        int key=array[start];
        int low=start,high=end;
        while (low<high){
            while (low<high&&array[high]>=key){//为什么需要等于?   2 .....  2
                high--;
            }
            while (low<high&&array[low]<key){//需要等于吗?   2 1
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (low!=start)quickSort(array,start,low);
        if (high!=end)quickSort(array,low+1,end);
    }
    private static void quickSort2(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            while (low<high&&array[low]<key){
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (start!=low)quickSort2(array,start,low);//low-1 low栈溢出
        if (end!=high)quickSort2(array,low+1,end);//直接low不加1  栈溢出
    }



    //快速排序
    public static void quickSort3(int s[], int l, int r)
    {
        if (l < r)
        {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j)
            {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j)
                    s[i++] = s[j];

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quickSort3(s, l, i - 1); // 递归调用
            quickSort3(s, i + 1, r);
        }
    }
}
