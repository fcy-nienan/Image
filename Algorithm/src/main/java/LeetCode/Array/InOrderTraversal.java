package LeetCode.Array;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;


public class InOrderTraversal {
    private static Logger logger = Logger.getLogger(InOrderTraversal.class.getName());

    public static void main(String args[]) throws Exception {

    }
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode current=root;
        List<Integer> res=new ArrayList<>();
        while (current!=null||!stack.empty()){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            res.add(current.val);
            current=current.right;
        }
        return res;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
