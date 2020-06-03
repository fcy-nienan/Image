package link;

import java.util.HashSet;
import java.util.Set;

public class numComponents {
    public static void main(String[] args) {
        ListNode listNode = LinkUtil.create(new int[]{1, 2, 3, 4, 5});
        int com=numComponents(listNode,new int[]{1,3,2,4});
    }
    public static int numComponents(ListNode head, int[] G) {
        if (G==null||G.length==0)return 0;
        if (head==null)return 0;
        int com=0;
        Set<Integer> set=new HashSet();
        for (int i : G) {
            set.add(i);
        }
        while (head!=null){
            if (set.contains(head.val)&&(head.next==null||!set.contains(head.next.val))){
                com++;
            }
            head=head.next;
        }
        return com;
    }
}
