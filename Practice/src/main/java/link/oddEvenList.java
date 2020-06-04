package link;

public class oddEvenList {
    public static void main(String[] args) {

    }
    public static ListNode oddEvenList(ListNode head) {
        ListNode oddDummy=new ListNode(-1),evenDummy=new ListNode(-1);
        ListNode oddP=oddDummy,evenP=evenDummy;
        ListNode current=head;
        while (true){
            if (current==null)break;
            oddP.next=current;
            if (current.next==null){//需要注意的点   1-2-3-4-5   如果直接break的话会造成最后一个5无法正确进入奇数链表
                oddP=oddP.next;
                oddP.next=null;
                break;
            }
            evenP.next=current.next;

            current=current.next.next;

            oddP=oddP.next;
            evenP=evenP.next;


            oddP.next=null;
            evenP.next=null;

        }
        oddP.next=evenDummy.next;
        return oddDummy.next;
    }
}
