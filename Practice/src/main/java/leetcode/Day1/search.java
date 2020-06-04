package leetcode.Day1;/*
 * Author:fcy
 * Date:2020/2/7 23:35
 */
/*
* 罗列所有的可能并进行不同处理
* 先判断数组是否旋转了
* 如果旋转了再通过二分法找出那个最大值的下标,这样就把这个数组分为了两段
* 然后根据target的值和nums[0]的值大小关系决定对前半部分做二分查找还是对后半部分做二分查找
* */
public class search {
    public static void main(String[] args) {
        String t=new String("3");
        System.out.println(t=="3");
    }
    public int search(int[] nums, int target) {
        if (nums==null||nums.length==0)return -1;
        int start=0,end=nums.length;
        if (nums[0]<nums[nums.length-1]){
            return binarySearch(nums, 0, nums.length - 1, target);
        }else{
            int mid=0;
            while (true){
                mid=(start+end)/2;
                if (mid-1<0)break;
                if (nums[mid]>nums[0]){
                    if (nums[mid]>nums[mid-1]){
                        start=mid+1;
                    }else{
//                        升序数组,这个else不可能存在
                    }
                }else{
                    if (nums[mid]>nums[mid-1]){
                        end=mid-1;
                    }else{
                        mid=mid-1;
                        break;
                    }
                }
            }
            if (target>=nums[0]){
                return binarySearch(nums,0,mid,target);
            }else{
                return binarySearch(nums,mid+1,nums.length-1,target);
            }
        }
    }
    public int binarySearch(int[] nums,int start,int end,int target){
        while (start<=end){
            int mid=(start+end)/2;
            if (target>nums[mid]){
                start=mid+1;
            }else if (target<nums[mid]){
                end=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
