package sort;

import java.util.Random;

public class sortUtil {
    public static void main(String args[]) {

    }
    public static void swap(int[] array,int i,int j){
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
    //    判断一个数组是否有序?
//    返回值:-1逆序  0无序   1 正序
//    这个判断数组是否有序的程序更加合理
//    首先是断言数组不为空,其次是如果数组是正序的或者逆序的那么是必须遍历数组所有成员的
//    但如果数组是无序的那么就只要发现数组前面的顺序和后面的顺序不相同就返回0,省去了遍历剩余的元素


    public static int checkArray2(int[] array){
        assert array!=null;
        if (array.length<2)return 1;
        int prev=array[0];
        boolean allEqual=true;
        int b=array[0]<array[array.length-1]?1:-1;

        if (b==1){//或许是正序
            for (int i=1;i<array.length;i++){
                if (array[i]<array[i-1]){
                    return 0;
                }
            }
            return b;
        }else{//或许是逆序或者全等
            for (int i=1;i<array.length;i++){
                if (array[i]>array[i-1]){
                    return 0;
                }
                if (array[i]!=prev){
                    allEqual=false;
                }
            }
            if (allEqual)return 1;
            return b;
        }


//
//        boolean allEqual=true;
//        for(int i=1;i<array.length;i++){
//            if (array[i]!=prev){
//                allEqual=false;
//            }
//            if (array[i]==array[i-1]){//如果存在相等的元素,那么跳过
//                continue;
//            }
//            if (b==1&&array[i]<array[i-1]){
//                return 0;
//            }
//            if (b==-1&&array[i]>array[i-1]){
//                return 0;
//            }
//        }
//        if (allEqual)return 1;
//        return b;
    }
    public static int checkArray(int[] array){
        int sequenceCount=0,reserveCount=0;
        int compareCount=array.length-1;
        for(int i=0;i<compareCount;i++){
            if (array[i]<array[i+1]){
                sequenceCount++;
            }else {
                reserveCount++;
            }
        }

        if (sequenceCount==compareCount){
            return 1;
        }else if (reserveCount==compareCount){
            return -1;
        }else{
            return 0;
        }
    }
    public static int[] produceArray(int len){
        Random random=new Random();
        int[] array=new int[len];
        for(int i=0;i<len;i++){
            array[i]=random.nextInt(len);
        }
        return array;
    }
}
