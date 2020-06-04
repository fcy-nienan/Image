package leetcode.Day1;/*
 * Author:fcy
 * Date:2020/2/7 1:01
 */

import java.util.ArrayList;
import java.util.List;

public class spiralOrder {
    public static void main(String[] args) {
        int m=3,n=4;
        int sum=1;
        int[][] arr=new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                arr[i][j]=sum++;
            }
        }
        for (Integer integer : new spiralOrder().spiralOrder(arr)) {
            System.out.println(integer);
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix==null||matrix.length==0)return new ArrayList<>();
        int left=0,right=matrix[0].length-1,top=0,bottom=matrix.length-1;
        List<Integer> list=new ArrayList<>();
        int i=0,j=0;
        while (left<=right&&top<=bottom){
            for (j=left;j<=right;j++){
                list.add(matrix[i][j]);
            }
            j--;
            for (i=top+1;i<=bottom;i++){
                list.add(matrix[i][j]);
            }
            i--;
//            这两个break必须要,挡住的是只有一行或者只有一列的情况
//            另外left>=right不能放在上面,会挡住正常的只有一行的情况
//            上面的两个for已经处理了只有一行和只有一列的情况了
            if (top>=bottom)break;
            if (left>=right)break;
            for (j=right-1;j>=left;j--){
                list.add(matrix[i][j]);
            }
            j++;
            for (i=bottom-1;i>top;i--){
                list.add(matrix[i][j]);
            }
            i++;
            left=left+1;
            right=right-1;
            top=top+1;
            bottom=bottom-1;
        }
        return list;
    }
}
