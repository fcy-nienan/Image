package link;

public class deleteNode {
    public static void main(String args[]) {

    }
//    删除给定的节点,node节点是需要被删除的节点,在之前我们可以通过prev.next.val判断下一个节点是否是需要删除的节点
//    然后通过prev.next=prev.next.next,下面这种方法通过将node.next的复制到当前节点,然后跳过下一个节点
    public static void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
