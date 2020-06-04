package link;
//给定 1->2->3->4, 你应该返回 2->1->4->3.
public class swapPairs {
    public static void main(String args[]) {
        ListNode head=LinkUtil.create(8);
        LinkUtil.disListNode(head);
        ListNode transfer=swapPairs(head);
        LinkUtil.disListNode(transfer);
    }
    public static ListNode swapPairs(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode two=head.next,prev=new ListNode(0),current=null,next=null;
        current=head;
        while (current!=null){
            if (current.next==null)break;
            next=current.next;

            current.next=next.next;
            next.next=current;

            prev.next=next;//特别需要注意的是这个prev,在进行下一轮交换的时候需要更新prev.next的值
            prev=current;

            current=current.next;
        }
        return two;
    }
}
