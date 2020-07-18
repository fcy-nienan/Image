package util;

import java.util.Arrays;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        int[] x=new int[]{2,7,11,15};
        int target=9;
        int[] ints = twoSum(x, target);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] results=new int[2];
        HashMap<Integer,Integer> map=new HashMap();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int result=target-nums[i];
            if (map.containsKey(result)&&i!=map.get(result)){
                results[0]=map.get(result);
                results[1]=i;
            }
        }
        return results;
    }
}
