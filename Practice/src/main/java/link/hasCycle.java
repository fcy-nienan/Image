package link;

import java.util.HashSet;
import java.util.Set;

public class hasCycle {
    public static void main(String args[]) {

    }
//    判断一个链表是否有环
//    violence:
//    使用空间记录访问过的节点,然后每次访问一个节点的时候判断该节点是否在已访问的节点中
    public static boolean hasCycleViolence(ListNode head){
        if (head==null)return false;
        if (head.next==null)return false;
        Set<ListNode> set=new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return true;
            }else{
                set.add(head);
            }
            head=head.next;
        }
        return false;
    }
//    快慢指针?
//    相当于两名速度不同的运动员在单向跑道上奔跑
//    如果跑道存在环,那么速度快的运动员肯定能追上速度慢的运动员
//    如果跑道不存在环,那么奔跑n/2次后速度快的的到了终点
    public static boolean hasCycle(ListNode head) {
        if (head==null||head.next==null)return false;
        ListNode fast,lower;
        fast=lower=head;
        while (fast!=null&&fast.next!=null){//需要特别注意判断fast.next为null的情况
            fast=fast.next.next;
            lower=lower.next;
            if (fast==lower)return true;
        }
        return false;
    }
}
