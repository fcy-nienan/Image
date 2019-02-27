package com.fcy.Algorithm.LeetCode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-20  22:23
 */
public class TwoSum {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{3,2,4},6)));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
        System.out.println(searchInsert(new int[]{1,2,3,5,8},4));
        System.out.println(-8>>>1);//00000001000
    }
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int rest=target-nums[i];
            if (map.containsKey(rest)&&map.get(rest)!=i){
                return new int[]{i,map.get(rest)};
            }
        }
        return new int[]{};
    }
    public static List<Integer[]> twoSumNormal(int[] nums, int target,int start,int end) {
        HashMap<Integer,Integer> map=new HashMap<>();
        List<Integer[]> list=new ArrayList<>();
        for(int i=start;i<end;i++){
            map.put(nums[i],i);
        }
        for(int i=start;i<end;i++){
            int rest=target-nums[i];
            if (map.containsKey(rest)&&map.get(rest)!=i){
                list.add(new Integer[]{i,map.get(rest)});
            }
        }
        return list;
    }
    public static int removeDuplicates(int[] nums) {
        int start=0,end=0;
        for(int i=1;i<nums.length;i++){
            if (nums[i]!=nums[start]){
                start++;
                nums[start]=nums[i];
            }
        }
        return start+1;
    }
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
    public static int searchInsert(int[] nums, int target) {
        int index=0;
        for(int i=0;i<nums.length;i++){
            if (nums[i]>=target) {
                index = i;
                return index;
            }else
                index++;
        }
        return index;
    }
    public boolean judgeCircle(String moves) {
        int i=0,j=0,m=0,n=0;
        for(char c:moves.toCharArray()){
            switch (c){
                case 'U' :i++;break;
                case 'D' :j++;break;
                case 'R' :m++;break;
                case 'L' :n++;break;
            }
        }
        if (i==j&&m==n)
            return true;
        return false;
    }
}
