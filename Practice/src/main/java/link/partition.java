package link;

public class partition {
    public static void main(String args[]) {
        ListNode head=LinkUtil.create(new int[]{1,4,3,2,5,2});
        ListNode result=partition(head,3);
        LinkUtil.disListNode(result);
    }
    public static ListNode partition(ListNode head, int x) {
        if (head==null||head.next==null)return head;
        ListNode dummyLess=new ListNode(0);
        ListNode dummyGrater=new ListNode(0);
        ListNode current=head,p=dummyLess,q=dummyGrater;
        while (current!=null){
            if (current.val<x){
                p.next=current;
                p=p.next;
            }else{
                q.next=current;
                q=q.next;
            }
            current=current.next;
        }
        q.next=null;//防止死循环
        p.next=dummyGrater.next;
        return dummyLess.next;
    }
}
