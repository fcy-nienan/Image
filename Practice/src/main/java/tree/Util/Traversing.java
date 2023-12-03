package tree.Util;

import tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversing {
    public static void main(String[] args) {
        Node node = TreeUtil.create(new int[]{1,2,3,4,5,6,7,8});
        postOrder2(node);
    }
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
    public static void visit(Node node){
        System.out.println(node.value);
    }
    public static void preOrder(Node root){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(!stack.empty()){
            Node node = stack.pop();
            visit(node);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }
    public static void inOrder(Node root){
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while(current!=null){
            stack.push(current);
            current = current.left;
        }
        while(!stack.empty()){
            Node node = stack.pop();
            visit(node);
            node = node.right;
            while(node !=null){
                stack.push(node);
                node = node.left;
            }
        }
    }
    public static void inOrderV1(Node root){
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while (current!=null || !stack.isEmpty()){
            if (current!=null){
                stack.push(current);
                current = current.left;
            }else{
                visit(current);
                current = current.right;
            }
        }
    }
    public static void postOrder(Node root){
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        stack.push(current);
        while(!stack.isEmpty()){
            if (current.right!=null){
                stack.push(current.right);
            } else if (current.left != null) {
                stack.push(current.left);
            }else{
                current = stack.pop();
                visit(current);
            }

        }
    }

    public static void preOrder1(Node root){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            visit(node);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }
    public static void inOrder1(Node root) {
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while (current!=null){
            stack.push(current);
            current=current.left;
        }
        while (!stack.isEmpty()){
            current = stack.pop();
            visit(current);
            current = current.right;
            while (current!=null){
                stack.push(current);
                current = current.left;
            }
        }
    }
    public static void postOrder2(Node root){
        Stack<Node> stack = new Stack<Node>();
        Stack<Integer> result = new Stack<Integer>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            visit(node);
            result.push(node.value);
            if (node.left!=null){
                stack.push(node.left);
            }
            if (node.right!=null){
                stack.push(node.right);
            }
        }
        System.out.println();
        while (!result.isEmpty()){
            System.out.println(result.pop());
        }
    }
}
