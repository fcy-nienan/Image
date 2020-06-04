package leetcode.Day1;/*
 * Author:fcy
 * Date:2020/2/7 2:03
 */

import java.util.*;

public class permute {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap();
        map.put("sdf","kj");
        for(int i=0;i<1000;i++){
            map.put("sdf"+i,"i+");
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        System.out.println(iterator.next().getKey());
    }
    public List<List<Integer>> permute(int[] nums) {
        if (nums==null||nums.length==0)return new ArrayList<>();
        List<List<Integer>> lists=new ArrayList<>();
        boolean[] used=new boolean[nums.length];
        dfs(lists,new ArrayList<Integer>(),used,0,nums);
        return lists;
    }
    /*
    def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择

    这里的used数组可以看作是一种剪枝
    重置used和移除current中的元素可以看作是撤销选择
    * */
    public void dfs(List<List<Integer>> res,List<Integer> current,boolean[] used,int index,int[] nums){
        if (current.size()==nums.length){
            res.add(new ArrayList<>(current));
        }else{
            for (int i=0;i<nums.length;i++){
                if (used[i])continue;
                current.add(nums[i]);
                used[i]=true;
                dfs(res,current,used,index+1,nums);
                used[i]=false;
                current.remove(index);
            }
        }
    }

}
