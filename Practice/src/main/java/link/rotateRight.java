package link;
public class rotateRight {
    public static void main(String args[]) {
        ListNode head=LinkUtil.create(8);
        LinkUtil.disListNode(head);
        head=rotateRight(head,3);
        LinkUtil.disListNode(head);
    }
    public static ListNode rotateRight(ListNode head,int k){
        if (k==0||head==null||head.next==null)
            return head;
        int len=LinkUtil.length(head);
        k=k%len;
        ListNode prev=null,current=head;
        for(int i=0;i<len;i++){
            if (i<len-k){
                prev=current;
            }
            if (i<len-1) {
                current = current.next;
            }
        }
        current.next=head;
        ListNode root=prev.next;
        prev.next=null;
        return root;
    }

}
