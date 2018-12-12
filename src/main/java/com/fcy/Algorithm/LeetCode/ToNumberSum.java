package com.fcy.Algorithm.LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @descripiton:    两数相加    一个数组    一个结果数
 *                  a[2,4,5,8]       10
 *                  返回结果[0,3]===>a[0]+a[3]=10
 * 思路:通过HashMap存储数组数据,然后用target减去当前数,再在map中查找是否相同的结果
 * @author: fcy
 * @date: 2018-12-07  0:43
 */
public class ToNumberSum {
    public static int[] toNumberSum(int[] numbers,int target){
        int[] results=new int[2];
        HashMap<Integer,Integer> map=new HashMap();
        for(int i=0;i<numbers.length;i++){
            map.put(numbers[i],i);
        }
        for(int i=0;i<numbers.length;i++){
            int result=target-numbers[i];
            if (map.containsKey(result)&&map.get(result)!=i){//后面的比较条件是防止两数相同
                results[0]=i;
                results[1]=map.get(result);
            }
        }
        return results;
    }
    //一般hash能成功是因为: 2+9=7   9-2=7   9-7=2   查找2或者7都可以
    public static int[] oneNumberSum(int[] numbers,int target){
        int[] results=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            int result=target-numbers[i];
            if (map.containsKey(result)){//这里不用比较是否相同,因为比较的时候当前数还没有添加进去
                results[0]=map.get(result);
                results[1]=i;
            }
            map.put(numbers[i],i);
        }
        return results;
    }

    public static void main(String[] args) {
        int[] data=new int[]{2,3,4,5};
        System.out.println(Arrays.toString(data));
        int target=8;
        int[] indexs=toNumberSum(data,target);
        System.out.println(Arrays.toString(indexs));
        indexs=oneNumberSum(data,target);
        System.out.println(Arrays.toString(indexs));
    }
}
