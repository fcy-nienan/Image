package com.fcy.Algorithm.LeetCode;

import lombok.val;

/**
 * @descripiton: 两数相加   注意 需要计算进位   链表操作需要格外小心
 * @author: fcy
 * @date: 2018-12-07  0:19
 */
public class ToNumberAdd {


    static class ListNode{
        private int val;
        private ListNode next;
        public ListNode(int x){
            this.val=x;
        }
    }
    public static ListNode addTowNumbers(ListNode node1,ListNode node2){
        ListNode result=new ListNode(0),q=result;
        int carry=0,tmp=0;
        ListNode m=node1,n=node2;
        int i=0,j=0;
        while(m!=null||n!=null){
            int r1=m==null?0:m.val;
            int r2=n==null?0:n.val;
            tmp=(r1+r2+carry)%10;//计算当前值
            carry=(r1+r2+carry)/10;//计算进位
            ListNode node=new ListNode(tmp);
            q.next=node;
            q=q.next;
            m=m.next;
            n=n.next;
        }
        if (carry>0)//如果最后还进位的话
            q.next=new ListNode(carry);
        return result.next;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(0),node2=new ListNode(0),p=node1,q=node2;
        for(int i=1;i<4;i++){
            p.next=new ListNode(i);
            p=p.next;
            q.next=new ListNode(i);
            q=q.next;
        }
        disListNode(node1);
        System.out.println();
        disListNode(node2);
        System.out.println();
        ListNode result=addTowNumbers(node1,node2);
        disListNode(result);
        System.out.println(getListNodeSize(result));
    }
    public static void disListNode(ListNode node){
        ListNode p=node;
        while(p!=null){
            System.out.print(p.val);
            p=p.next;
        }
    }
    public static int getListNodeSize(ListNode node){
        if (node==null)
            return 0;
        return getListNodeSize(node.next)+1;
    }
}
