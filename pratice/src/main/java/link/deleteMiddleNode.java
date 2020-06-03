package link;
public class deleteMiddleNode {
    public static void main(String args[]) throws Exception {
        ListNode listNode = LinkUtil.create(4);
        LinkUtil.disListNode(listNode);
        deleteNode(listNode);
        LinkUtil.disListNode(listNode);
    }
    public static void deleteNode(ListNode node) {
        ListNode dummp=new ListNode(-1,node);
        ListNode slower=node,faster=node,pre=dummp;
        while(true){
            if(faster==null||faster.next==null||faster.next.next==null){
                pre.next=slower.next;
                slower.next=null;
                break;
            }
            faster=faster.next.next;
            slower=slower.next;
            pre=pre.next;
        }
    }
}
