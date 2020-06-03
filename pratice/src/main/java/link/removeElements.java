package link;

public class removeElements {
    public static void main(String args[]) {
        ListNode node=LinkUtil.create(new int[]{1,2,3,4,5,6});
        node=removeElements(node,6);
        LinkUtil.disListNode(node);

    }
    public static ListNode removeElements(ListNode head, int val){
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode current=head,prev=dummy;
        while (current!=null){
            if (current.val==val){
                current=current.next;
                prev.next=current;
            }else{
                current=current.next;
                prev=prev.next;//忘了更新prev指针的值
            }
        }
        return dummy.next;
    }
//    删除链表中给定值的第一个节点
    public static ListNode removeElementsOne(ListNode head, int val) {
        ListNode p=new ListNode();
        p.next=head;
        while (p.next!=null&&p.next.val!=val){
            p=p.next;
        }
        if (p.next!=null){
            p.next=p.next.next;
        }
        return head;

    }
}
