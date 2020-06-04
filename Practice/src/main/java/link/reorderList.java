package link;

import java.util.ArrayList;
import java.util.List;

public class reorderList {
    public static void main(String[] args) {
        ListNode listNode = LinkUtil.create(5);
        LinkUtil.disListNode(listNode);
        reorderList2(listNode);
        LinkUtil.disListNode(listNode);
    }
    /*
    * 链表值  1--100
    * 排序成   1 100 2 99 3 98 ..... 50  51
    *
    * */
    public static void reorderList(ListNode head) {
        ListNode current=head,last=head,p=null;
        while (true){
            last=current;
            if (last==null){
                break;
            }
            while (last.next!=null){
                p=last;
                last=last.next;
            }
            if(p!=null)p.next=null;
            if (current==last) {
                break;
            }
            last.next=current.next;
            current.next=last;
            current=current.next.next;
        }
    }
    public static void reorderList1(ListNode head){
        if (head==null||head.next==null||head.next.next==null)return;
        List<ListNode> list=new ArrayList<>();
        ListNode p=head;
        while (p!=null){
            list.add(p);
            p=p.next;
        }
        ListNode current=head;
        int threshold=list.size()%2==0?list.size()/2:list.size()/2+1;
        while (true){
            if (threshold==list.size())break;
            ListNode last=list.get(list.size()-1);
            ListNode pre=list.get(list.size()-2);
            pre.next=null;
            last.next=current.next;
            current.next=last;
            current=current.next.next;
            list.remove(list.size()-1);
        }
    }
    public static void reorderList2(ListNode head){
        if (head==null||head.next==null||head.next.next==null)return;
        ListNode slower=head,faster=head;
        while (true){
            if (faster==null||faster.next==null||faster.next.next==null||faster.next.next.next==null)break;
            slower=slower.next;
            faster=faster.next.next;
        }
        //分割成两个链表
        ListNode one=head,two=slower.next;
        slower.next=null;

        //反转第二个链表
        ListNode current=two,prev=null,next=null;
        while (current!=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        two=prev;

        ListNode twoNext=null;
        while (one!=null&&two!=null){
            twoNext=two.next;

            two.next=one.next;
            one.next=two;

            two=twoNext;
            if (one.next.next==null){
                break;
            }
            one=one.next.next;
        }
        one.next.next=twoNext;
    }
}
