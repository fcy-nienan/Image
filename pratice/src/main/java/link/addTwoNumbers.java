package link;

import java.util.logging.Logger;

public class addTwoNumbers {
    private static Logger logger = Logger.getLogger(addTwoNumbers.class.getName());

    public static void main(String args[]) throws Exception {
        ListNode listNode1 = LinkUtil.create(new int[]{1});
        ListNode listNode = LinkUtil.create(new int[]{9,9});
        LinkUtil.disListNode(listNode1);
        LinkUtil.disListNode(listNode);
        ListNode listNode2 = addTwoNumbers(listNode1, listNode);
        LinkUtil.disListNode(listNode2);
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode listNode=new ListNode(-1,null);
        int l1val=0,l2val=0;
        ListNode cur=listNode;
        while(true){
            if (l1==null&&l2==null&&carry==0)break;
            l1val=l1==null?0:l1.val;
            l2val=l2==null?0:l2.val;
            cur.next=new ListNode((l1val+l2val+carry)%10);
            carry=(l1val+l2val+carry)/10;
            cur=cur.next;
            l1=l1==null?l1:l1.next;
            l2=l2==null?l2:l2.next;
        }
        return listNode.next;
    }
}
