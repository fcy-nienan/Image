package link;

public class mergeKList {
    public static void main(String[] args) {
        ListNode listNode = LinkUtil.create(10);
        ListNode listNode1 = LinkUtil.create(8);
        ListNode listNode2 = LinkUtil.create(20);
        ListNode listNode4=LinkUtil.create(3);
        listNode=insertSortList.insertionSortList(listNode);
        listNode1=insertSortList.insertionSortList(listNode1);
        listNode2=insertSortList.insertionSortList(listNode2);
        listNode4=insertSortList.insertionSortList(listNode4);
        LinkUtil.disListNode(listNode);
        LinkUtil.disListNode(listNode1);
        LinkUtil.disListNode(listNode2);
        LinkUtil.disListNode(listNode4);
        ListNode[] lists=new ListNode[]{listNode,listNode1,listNode2,listNode4};
        ListNode listNode3=mergeKListsNoRecursive(lists);
        LinkUtil.disListNode(listNode3);


    }
    /*非递归  2 4 8 16  合并*/
    public static ListNode mergeKListsNoRecursive(ListNode[] lists){
        if (lists.length==0)return null;
        int len=lists.length;
        int index=1;
        while (true){
            if (index>=len)break;
            index=index<<1;
            int increment=index>>1;
            for (int i=0;i<len;i+=index){
                if (i+increment>=len)break;
                lists[i]=mergeTwoList(lists[i],lists[i+increment]);
            }
        }
        return lists[0];
    }

    /*递归方式两两合并*/
    public static ListNode mergeKLists1(ListNode[] lists){
        if (lists.length==0)return null;
        return merge(lists,0,lists.length);
    }
    public static ListNode merge(ListNode[] lists,int start,int end){
        if (start<end){
            int mid=(start+end)>>1;
            return mergeTwoList(merge(lists,start,mid),merge(lists,mid+1,end));
        }else{
            return lists[start];
        }
    }

    /*时间复杂度k^2*n */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0)return null;
        ListNode p1=lists[0];
        for (int i=1;i<lists.length;i++){
            p1=mergeTwoList(p1,lists[i]);
            LinkUtil.disListNode(p1);
        }
        return p1;
    }
    public static ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode dummy=new ListNode(-1),p=dummy;
        while (true){
            if (l1==null||l2==null)break;
            if (l1.val>l2.val){
                p.next=l2;
                l2=l2.next;
            }else{
                p.next=l1;
                l1=l1.next;
            }
            p=p.next;
            p.next=null;
        }
        if (l1!=null){
            p.next=l1;
        }else if(l2!=null){
            p.next=l2;
        }
        return dummy.next;
    }
}
