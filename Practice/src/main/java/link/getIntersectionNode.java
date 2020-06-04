package link;

public class getIntersectionNode {
    public static void main(String args[]) {

    }
//    找出链表相交的节点
//    violence
//    对于A链表的每一个节点,遍历整个B链表,若地址相同则相交
//    Hash
//    遍历A链表并存储到Set中,然后遍历B链表,判断是否存在地址相同的节点
//    two pointer
//    两个指针p,q,分别初始化为想要的链表头,然后p走到A链表的结尾的时候再走B链表,q链表走到B链表的结尾的时候再走A链表
//    这样如果存在相交的节点那么第二次走的时候在相同的次数中一定会相等
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null||headB==null)return null;
        ListNode p=headA,q=headB;
        boolean flagA=false,flagB=false;
        while (p!=null&&p!=q){
            p=p.next;
            q=q.next;
            if (p==null){
                if (flagA)
                    return null;
                p=headB;
                flagA=true;

            }
            if (q==null){
                if (flagB)
                    return null;
                q=headA;
                flagB=false;
            }
        }
        return p;
    }
    public static ListNode getIntersectionNodeI(ListNode A,ListNode B){
        if (A==null||B==null)return null;
        ListNode q=A,p=B;
        while (q!=p){//这里是如果第一次为null的话就会转换链表,如果第二次为null的话那么就直接退出了null==null
//            如果第一次都到达了重点且为null那么刚好返回的也是null
            p=p==null?A:p.next;
            q=q==null?B:q.next;
//            if (p==null){
//                p=A;
//            }else{
//                p=p.next;
//            }
//            if (q==null){
//                q=B;
//            }else{
//                q=q.next;
//            }
        }
        return q;
    }
}
