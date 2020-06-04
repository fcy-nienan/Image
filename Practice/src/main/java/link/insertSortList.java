package link;

public class insertSortList {
    public static void main(String[] args) {
        ListNode listNode = LinkUtil.create(4);
        LinkUtil.disListNode(listNode);
        listNode=insertionSortList(listNode);
        LinkUtil.disListNode(listNode);
    }
    /*
    * -1,4,3,2,1
    * p=4
    * prev=-1
    * current=3
    * currentPrev=4
    * */
    public static ListNode insertionSortList(ListNode head){
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode(-1,head);
        ListNode current=head.next,currentPrev=head;
        ListNode prev=null,p=null;
        while (current!=null){
            prev=dummy;
            p=prev.next;
            while (current.val>p.val){
                prev=p;
                p=p.next;
            }
            if (current==p){
                current=current.next;
                currentPrev=currentPrev.next;
            }else {
                currentPrev.next = currentPrev.next.next;

                current.next = p;
                prev.next = current;

                current = currentPrev.next;
            }
        }
        return dummy.next;
    }
}
