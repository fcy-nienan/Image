package Link;

import java.util.logging.Logger;

public class LinkUtil {

//    删除重复节点
    public static LinkNode distinct(LinkNode node){
        return node;
    }
//    反转链表
    public static LinkNode reverse(LinkNode node){
        return node;

    }
    public static void remove(LinkNode node){
        node.val=node.next.val;
        node.next=node.next.next;
    }
//    两个链表相交的节点
    public static LinkNode intersetNode(LinkNode headA,LinkNode headB){
        return null;

    }
//    是否有环
    public static boolean hasCycle(LinkNode node){

        return false;
    }
//    链表长度
    public static int length(LinkNode node){

        return 0;
    }
//    创建链表
    public static LinkNode create(int[] array){

        return null;
    }
//    打印链表
    public static String toString(LinkNode node){

        return null;
    }
//    链表中删除某个元素
    public static LinkNode removeElement(LinkNode node,int x){

        return node;
    }
//  删除倒数第几个节点
    public static LinkNode removeNthFromEnd(LinkNode node,int x){
        return null;

    }
//
    public static LinkNode rotateRight(LinkNode node,int x){
        return null;

    }
//  两两交换
    public static LinkNode swapPairs(LinkNode node,int x){
        return null;

    }

    public static void main(String[] args) {
        Thread t=new Thread(()->{
            System.out.println("sdf");
        });
    }
}
