package link;

import java.util.HashMap;
import java.util.Map;

//给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
public class    removeNthFromEnd {
    public static void main(String args[]) {
        ListNode head=LinkUtil.create(8);
        LinkUtil.disListNode(head);
        ListNode removed=removeNthFromEnd(head,3);
        LinkUtil.disListNode(removed);
        removed=fastLowPointer(removed,4);
        LinkUtil.disListNode(removed);
    }
//    通过快慢指针,快指针到达最后一个节点的时候,慢指针刚好到达需要删除的节点的前一个结点
    public static ListNode fastLowPointer(ListNode head,int n){
        ListNode faster=head,slow=head;
        while (n-->0){
            faster=faster.next;
        }
        if (faster==null)
            return head.next;
        while (faster.next!=null){
            faster=faster.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer,ListNode> map=new HashMap();
        ListNode p=head;
        int i=0;
        while (p!=null){
            map.put(++i,p);
            p=p.next;
        }
        int index=i-n+1;
        ListNode prev=map.get(index-1),next=map.get(index+1);
        if (prev!=null){
            prev.next=next;
            return head;
        }else if (next!=null){
            return next;
        }else{
            return null;
        }
    }
}
