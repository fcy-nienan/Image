package link;

public class reverseList {
    public static void main(String args[]) {
        ListNode head=LinkUtil.create(new int[]{1,2,3,4,5,6});
        ListNode reversed=reverseList(head);
        LinkUtil.disListNode(reversed);
    }
//    反转链表 1-2-3-4-5-null=========5-4-3-2-1-null
    public static ListNode  reverseList(ListNode head) {
        ListNode current=head,prev=null,next=null;
        while (current!=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        return prev;
    }
//    迭代或者递归?
}
