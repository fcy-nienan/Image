package sort;

import util.ListUtil;

//插入排序
//时间复杂度?空间复杂度?稳定性?
//时间复杂度o(n*n)  空间复杂度o(1)  稳定性:是
public class insertSort extends AbstractSort {
    public static void main(String args[]) {
        insertSort insertSort=new insertSort();
        insertSort.execute();
        ListUtil.disListLen(insertSort.disOrderList,5);
    }
    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int tmp=array[i];
            int j=i-1;
            while (j>=0&&tmp<=array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=tmp;
        }
    }

    @Override
    protected void sort(int[] array) {
        insertSort(array);
    }
}
