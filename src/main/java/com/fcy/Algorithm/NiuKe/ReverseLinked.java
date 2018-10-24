package com.fcy.Algorithm.NiuKe;

import java.util.ArrayList;

/**
 * @descripiton:    将链表逆序转化为数组
 * @author: fcy
 * @date: 2018-08-20  23:40
 */
public class ReverseLinked {
    public static void main(String args[]) {
        ListNode node=new ListNode(3);
        node.next=new ListNode(4);
        node.next.next=new ListNode(5);
        printListFromTailToHead(node).forEach(e->{
            System.out.println(e);
        });
    }
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> integers=new ArrayList<>();
        if(listNode==null){
            return integers;
        }
        while(listNode!=null){
            integers.add(listNode.val);
            listNode=listNode.next;
        }
        for(int i=0,j=integers.size()-1;i!=j&&i<j;i++,j--){
            System.out.println(i+" "+j);
            int tmpi=integers.get(i);
            int tmpj=integers.get(j);
            integers.set(i,tmpj);
            integers.set(j,tmpi);
        }
        return integers;
    }
    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
}

