package leetcode.Day1;/*
 * Author:fcy
 * Date:2020/2/7 0:27
 */

import java.util.ArrayList;
import java.util.List;

/*
* 给定数组返回子集
* 1,2,3
* 1
* 2
* 3
* 12
* 13
* 23
* 123
*
* */
public class subSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        lists.add(new ArrayList<>());
        int count= (int) Math.pow(2,nums.length);
        for (int i=1;i<count;i++){
            List<Integer> list=new ArrayList<>();
            for (int j=0;j<nums.length;j++){
                int a=1<<j;
                /*
                * 重点在这个i&a>0上,比如5,当碰到第一个和第四个的时候
                * 5:101
                * j=0,a=1,a=001,101&001>0
                * j=1,a=2,a=010,101&010=0
                * j=2,a=4,a=100,101&100>0
                * 这里的bit值0代表不取这个数,1代表取这个数
                * */
                if ((i&a)>0){
                    list.add(nums[j]);
                }
            }
            lists.add(list);
        }
        return lists;
    }

}
