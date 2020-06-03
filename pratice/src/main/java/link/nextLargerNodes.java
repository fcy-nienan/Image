package link;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class nextLargerNodes {
    public static void main(String[] args) {

    }
    public int[] getNextLargeNodes(ListNode head){
        if (head==null)return null;
        List<Integer> list=new ArrayList();
        ListNode p=head;
        while (p!=null){
            list.add(p.val);
            p=p.next;
        }
        Stack<Integer> stack=new Stack<>();
        int[] answer=new int[list.size()];
        for (int i=list.size()-1;i>=0;i--){
            while (!stack.empty()&&list.get(i)>=stack.peek()){
                stack.pop();
            }
            answer[i]=stack.isEmpty()?0:stack.peek().intValue();
            stack.push(list.get(i));
        }
        return answer;
    }
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1. 创建一个容器 list 来存储数据
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;
        while (head != null) {
            list.add(head.val);
            size++;
            head = head.next;
        }
        // 2. 创建一个栈 stack ，这个栈里面存储的是对应位置的 list 元素及其之后元素中最大的值。
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[size];
        // 3. 在 list 中从右往左遍历，stack 中凡是比 list.get(i) 小的都 pop 出去，
        // 这样 stack 剩下的元素都是比 list.get(i) 更大的元素。
        for (int i = list.size() - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }
            ans[i] = stack.empty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return ans;
    }
}
