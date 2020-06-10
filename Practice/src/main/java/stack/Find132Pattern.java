package stack;

import util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Find132Pattern {
    private static Logger logger = Logger.getLogger(Find132Pattern.class.getName());

    public static void main(String args[]) throws Exception {
        List<String> list=new ArrayList<>();
        int[] nums=new int[]{1,2,3,4,5};
        boolean[] flags=new boolean[nums.length];
        one_p(list,nums,flags,"",1);

        ListUtil.disList(list);
        System.out.println(list.size());
//        find132pattern(nums);
    }
    //Cnm=n!/m!*(n-m)!
    //Anm=n!/(n-m)! 5!/(5-3)!=60
    //排列   组合   123      123 132 213 231 321 312
    // A33=3!=1*2*3=6   A52=5!/3!=20
    // C33=1    C53=5!/(3!*2!)=10

    public static void one_c(List<String> list,int[] nums,boolean[] flags,String value,int len){
        if (len==0){
            list.add(value);
            return;
        }else{
            for (int i=0;i<nums.length;i++){
                if (flags[i])continue;
                flags[i]=true;
            }
        }
    }
    //纯粹的排列A53=5!/(5-3)!=5!/2!=3*4*5=60
    public static void one_p(List<String> list,int[] nums,boolean[] flags,String value,int len){
        if (len==0){
            list.add(value);
            return;
        }else{
            for (int i=0;i<nums.length;i++){
                if (flags[i])continue;
                flags[i]=true;
                one_p(list,nums,flags,nums[i]+value,len-1);
                flags[i]=false;
            }
        }
    }
    public static void getAllSequence(List<String> list,int[] nums,boolean[] flags,String value,int current,int len){
        if (current==len){
            list.add(value);
            return;
        }else{
            for (int i=current;i<nums.length;i++){
                if (!flags[i]) {
                    flags[i]=true;
                    getAllSequence(list, nums, flags, value + nums[i],current + 1,len);
                    flags[i]=false;
                }
            }
        }
    }
    public static boolean find132pattern(int[] nums) {
        getAllSequence1(nums,new boolean[nums.length],new int[3],0,0);
        return true;
    }
    public static boolean is132Pattern(int[] nums){
        return nums[0]<nums[2]&&nums[2]<nums[1];
    }
    public static void getAllSequence1(int[] nums,boolean[] flags,int[] result,int index,int current){
        if (current==3){
            if (is132Pattern(result)) {
                System.out.println(Arrays.toString(result));
            }
            return;
        }else{
            for (int i=current;i<nums.length;i++){
                if (!flags[i]) {
                    flags[i]=true;
                    result[index]=nums[i];
                    getAllSequence1(nums, flags, result,index+1,current + 1);
                    flags[i]=false;
                }
            }
        }
    }
}
