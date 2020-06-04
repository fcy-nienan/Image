package sort.V1;

import sort.AbstractSort;
import sort.sortUtil;

/*
* 每种排序算法的时间复杂度
* 每种排序算法是否稳定
* *
*
* */
public class Sort extends AbstractSort {
    public static void main (String args[]) {
        Sort sort=new Sort();
        sort.execute();
    }

    @Override
    protected void sort (int[] array) {
        shellSort(array);
    }
    public static void bubbleSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            for (int j=0;j<array.length-i-1;j++){
                if (array[j]>array[j+1]){
                    sortUtil.swap(array,j,j+1);
                }
            }
        }
    }
    public static void selectSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int minIndex=i;
            int j=i+1;
            for (;j<array.length;j++){
                if (array[minIndex]>array[j]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,minIndex,i);
        }
    }
//    插入排序每个之前的序列都是有序的
//    1，5，6，3    1，3，5，6
    public static void insertSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int j=i+1;
//            将j槽位空出来,大于array[j]的值都往后移
            int tmp=array[j];
//            2020/1/28:忘了j-1>=0这个条件
//            while (j-1>=0&&array[j]<array[j-1]){
//            2020/1/28:与之前的元素比较的时array[j]的值,而不是其相邻的两个的值
            while (j-1>=0&&tmp<array[j-1]){
              array[j]=array[j-1];
              j=j-1;
            }
//            直到array[j]>array[j-1]此时j的位置就可以防止之前j的值
            array[j]=tmp;
        }
    }
    private void mergeSort(int[] array){
        mergeSort(array,0,array.length-1);
    }
    private void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    private void merge(int[] array,int start,int mid,int end){
        int i=start,j=mid,m=mid+1,n=end;
        int[] tmp=new int[end-start+1];
        int k=0;
        while (i<=j&&m<=n){
//            2020/1/28:这个应该是打错了
            if (array[i]<array[m]){
//            if (array[i]<array[j]){
                tmp[k++]=array[i++];
            }else{
                tmp[k++]=array[m++];
            }
        }
        while (i<=j){
            tmp[k++]=array[i++];
        }
        while (m<=n){
            tmp[k++]=array[m++];
        }
        k=0;
        while (start<=end){
            array[start++]=tmp[k++];
        }
    }
    private void heapSort(int[] array){
//        将其调整为一棵大顶堆,元素上升构建,其所有节点都符合大顶堆的性质,所以需要遍历所有的非叶子节点
//        上升构建也需要两层循环
        for (int i=array.length/2-1;i>=0;i--){
            for (int k=i;k<=array.length/2-1;k++){
                int left=k*2+1;
                int right=left+1;
                int current=left;
                if (right<array.length&&array[right]>array[current]){
                    current=right;
                }
                if (array[k]<array[current]){
                    sortUtil.swap(array,k,current);
                }
            }
        }
//        for (int i=array.length/2-1;i>=0;i--){
//            int left=i*2+1;
//            int right=left+1;
//            int current=left;
//            if (right<array.length&&array[right]>array[current]){
//                current=right;
//            }
//            if (array[i]<array[current]){
//                sortUtil.swap(array,i,current);
//            }
//        }
//        堆调整i次,由于上面已经调整了一次,所以这里从1开始,下面的array.length传入的参数也不用减去1了
        for (int i=1;i<array.length;i++){
//        for (int i=0;i<array.length-1;i++){
            sortUtil.swap(array,0,array.length-i);
//            sortUtil.swap(array,0,array.length-1-i);
//            当大顶堆的堆顶是一个最小值时,此时堆顶元素需要下降,由于之前构造了大定堆,所以最大的元素已经在根节点的左右孩子里面了
//            但是堆顶元素可能时很小的,所以需要将其换到合适的位置保持大顶堆的性质
            /*
            *           4
            *       5       6
            *   1       2  3    0
            * */
//            下降我们只需要纠正一条线上的节点恢复为大顶堆性质,所以不需要遍历所有节点
//            j<=   从j=0到j<=array.length-i/2-1最后一个节点
//            二叉树的性质:一棵树的最后一个叶子节点在数组中的下标是:(array.length/2)-1,长度乘以2再减去1
//            二叉树的性质:根节点的两个子节点的位置:i*2+1,i*2+2
//            堆排序第一步是上身,第二步是堆每一个末尾的值进行下降
//            上升过程中由于上面的节点可能非常小,所以堆每一个上面的节点替换下来的值,已经处理过的下面的节点也需要再
//            处理一遍
//            而下降过程中不需要重复处理,下降过程中只是一条线的节点需要处理,但下降过程也有两重循环
//            下降过程中需要堆每一个末尾的值和整棵树的根节点交换值后进行下降
            for (int j=0;j<=((array.length-i)/2)-1;j++){
                int left=j*2+1;
                int right=left+1;
                int current=left;
//                right小于的时当前数组的长度
//                if (right<=(array.length-i)/2-1&&array[current]<array[right]){
                if (right<array.length-i&&array[current]<array[right]){
                    current=right;
                }
                if (array[j]<array[current]){
                    sortUtil.swap(array,j,current);
                }
            }
        }

    }
    public void quickSort(int[] array){
        quickSort(array,0,array.length-1);
    }
    private void quickSort(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        while (low<high){
//            这个地方array[high]>=key的等于需要特别注意,
//            两个while同时有或者任意while一个任意if一个
            while (low<high&&array[high]>=key){
                high--;
            }
            if (key>array[high]){
                sortUtil.swap(array,low,high);
            }
            while (low<high&&array[low]<key){
                low++;
            }
            if (key<=array[low]){
                sortUtil.swap(array,low,high);
            }
        }
        if (low!=start)quickSort(array,start,low-1);
        if (high!=end)quickSort(array,low+1,end);
    }
    public static void shellSort(int[] array){
        int gap=array.length;
        while (true){
            gap=gap/2;
            if (gap==0)return;
            for (int i=0;i<gap;i++){
                for (int j=i+gap;j<array.length;j+=gap){
                    int tmp=array[j];
                    int k=j;
//                    2020/1/28:又是这个位置。。。应该使用tmp来比较,而不是array[j]和array[j-1]
                    while ((k-gap)>=0&&tmp<array[k-gap]){
                        array[k]=array[k-gap];
                        k=k-gap;
                    }
                    array[k]=tmp;
                }
            }
        }
    }
}
