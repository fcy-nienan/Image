package stack;

import java.util.Stack;
import java.util.logging.Logger;

public class Find132Pattern {
    private static Logger logger = Logger.getLogger(Find132Pattern.class.getName());

    public static void main(String args[]) throws Exception {

    }


    public static boolean find132pattern(int[] nums) {
        if (nums==null||nums.length<3)return false;
        Stack stack=new Stack();
        int left=0,mid=1,right=2;
        while (true){
            if (nums[left]<nums[right]){
                if(nums[right]<nums[mid]){

                }
            }
            int leftValue=nums[left];
            int midValue=nums[mid];
            int rightValue=nums[right];
            if (leftValue<midValue&&rightValue<midValue){
                return true;
            }else{//123 132 213 231 312 321

                left+=1;
                mid+=1;
                right+=1;
            }
        }
    }
}
