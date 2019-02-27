package com.fcy.Algorithm.LeetCode.Practice.DayOne;

import java.util.Arrays;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-16  16:38
 */
public class ArraysPractice {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;
        merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }
//  思路: 1,2,3       2,5,6   将2插入的时候一次比较1,2,3,得到index位2,然后移动数组1,将数组1的第二位赋值为2
//    然后比较5,通过前一步已经知道index为2,所以可以从2开始遍历得到下一个index(不用重新从0开始)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index=0;
        for(int i=0;i<n;i++){
            for(int j=index;j<m;j++){
                if (nums2[i]>=nums1[j]){
                    index++;
                }else
                    break;
            }
            for(int k=m;k>index;k--){
                nums1[k]=nums1[k-1];
            }
            nums1[index]=nums2[i];
            m++;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode returnNode=new ListNode(0);
        ListNode current=returnNode;
        while (l1!=null&&l2!=null){
            if (l1.val>l2.val){
                current.next=l2;
                current=current.next;
                l2=l2.next;
            }else{
                current.next=l1;
                current=current.next;
                l1=l1.next;
            }
        }
        if (l1==null){
            current.next=l2;
        }else
            current.next=l1;
        return returnNode.next;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
