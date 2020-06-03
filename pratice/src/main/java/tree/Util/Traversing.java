package tree.Util;

import tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversing {
//    前序遍历
    public static List<Integer> preOrderStack(Node root){
        List<Integer> list=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            Node node=stack.pop();
            list.add(node.value);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
        return list;
    }
    public static List<Integer> inOrderStackII(Node root){
        List<Integer> list=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        Node node=root;
        while (node!=null||!stack.empty()){
            if (node!=null){
                stack.push(node);
                node=node.left;
            }else{
                Node n=stack.pop();
                list.add(n.value);
                node=node.right;
            }

        }
        return list;
    }
//    中序遍历
    public static List<Integer> inOrderStack(Node root){
        List<Integer> list=new ArrayList<>();
        if (root==null)return list;
        Stack<Node> stack=new Stack<>();
        Node node=root;
        while (node!=null){
            stack.push(node);
        }
        while (!stack.empty()){
            Node n=stack.pop();
            System.out.println(n.value);
            n=n.right;
            while (n!=null){
                stack.push(n);
                n=n.left;
            }
        }
        return list;
    }
    /*
    * 1 3   2 7 8 6 9 5
    *                   1
    *           2               3
    *
    *       5       7
    *             6    8
    *           9
    *
    *
    *
    * */
    public static List<Integer> postOrderStack(Node root){
        List<Integer> list=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        Node node=root,prev=null;
        while (null!=node||!stack.empty()){
            if (node!=null){
                stack.push(node);
                if (node.right!=null){
                    prev=node;
                    node=node.right;
                }else if (node.left!=null){
                    prev=node;
                    node=node.left;
                }else{
                    node=prev.left;
                }
            }
        }
        return list;
    }
}
