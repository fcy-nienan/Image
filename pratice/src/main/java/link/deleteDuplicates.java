package link;

public class deleteDuplicates {
    public static void main(String args[]) {
        ListNode root=LinkUtil.create(new int[]{1,2,3,3,4,4,5});
        root=deleteDuplicatesII(root);
        LinkUtil.disListNode(root);
    }
    //输入: 1->2->3->3->4->4->5
    //输出: 1->2->5
    public static ListNode deleteDuplicatesII(ListNode head){
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode(-1);
        ListNode first=head,second=head.next,p=dummy;
        boolean flag=false;
//        特殊的几个    1-1-1       1-2-3-4-5   1-2-3-3-3
        while (second!=null){
            while (second!=null&&second.val==first.val){
                second=second.next;
                flag=true;
            }
            if (second==null)break;
            if (flag){//说明存在重复的
                first=second;
                second=first.next;
            }else{//不存在重复的
                p.next=first;
                p.next.next=null;
                p=p.next;

                first=second;
                second=first.next;
            }
            flag=false;
        }
        if (!flag) {
            p.next = first;
            p.next.next = null;
        }
        return dummy.next;
    }
    //输入: 1->1->2
    //输出: 1->2
    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode(0,head);
        ListNode prev=dummy.next,current=prev.next;
        while (current!=null){
            if (prev.val==current.val){
                prev.next=current.next;
                current=current.next;
            }else{
                prev=current;
                current=current.next;
            }
        }
        return dummy.next;
    }
    public ListNode deleteDuplicates1(ListNode head) {
        //baseCase
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        //如果是这种情况
        //      1 --> 1 --> 1 --> 2 --> 3
        //     head  next
        //1.则需要移动next直到出现与当前head.value不相等的情况（含null）
        //2.并且此时的head已经不能要了，因为已经head是重复的节点
        //--------------else-------------
        //      1 --> 2 --> 3
        //     head  next
        //3.如果没有出现1的情况，则递归返回的节点就作为head的子节点
        if (head.val == next.val) {
            //1
            while (next != null && head.val == next.val) {
                next = next.next;
            }
            //2
            head = deleteDuplicates(next);
        } else {
            //3
            head.next = deleteDuplicates(next);
        }
        return head;
    }
    public ListNode deleteDuplicates2(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode(-1);
        ListNode first=head,second=head.next,p=dummy;
        boolean flag=false;
        //        特殊的几个       1-2-3-4-5   1-2-3-3-3
        while (second!=null){
            while (second!=null&&second.val==first.val){
                second=second.next;
                flag=true;
            }
            if (second==null)break;
            if (flag){//说明存在重复的
                first=second;
                second=first.next;
            }else{//不存在重复的
                p.next=first;
                p.next.next=null;
                p=p.next;

                first=second;
                second=first.next;
            }
            flag=false;
        }
        if (!flag) {
            p.next = first;
            p.next.next = null;
        }
        return dummy.next;
    }
}
