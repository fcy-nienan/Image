package stack;

import tree.Node;
import tree.Util.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

//输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
public class inorderTraversal {
    private static Logger logger = Logger.getLogger(inorderTraversal.class.getName());

    public static void main(String args[]) throws Exception {
        Node root= TreeUtil.create(new int[]{3,1,-1,-1,2});
        TreeUtil.preOrder(root);
        root=new Node(3);
        root.left=new Node(1);
        root.left.right=new Node(2);
        List<Integer> list=inorderTraversal(root);
        System.out.println(list);
    }
    public static List<Integer> inorderTraversal(Node root) {
        if (root==null)return new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root=root.left;
            }else{
                root=stack.pop();
                list.add(root.value);
                root=root.right;
            }
        }
        return list;
    }
    public static List<Integer> inorderStack(Node root){
        Stack<Node> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        Node current=root;
        while (current!=null||!stack.empty()){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            list.add(current.value);
            current=current.right;
        }
        return list;
    }
}
