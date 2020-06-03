package tree.AVL.V9;/*
 * Author:fcy
 * Date:2020/2/15 15:06
 */

import tree.AVLNode;

import java.util.Stack;

public class AVLV9Tree {
    private AVLNode root;
    public int height(AVLNode node){
        if(node==null) {
            return 0;
        }
        return node.height;
    }
    public int balance(AVLNode node){
        if(node==null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }
    public AVLNode LL(AVLNode node){
        AVLNode x=node.right;
        AVLNode tmp=x.left;

        node.right=tmp;
        x.left=node;

        node.height=1+Math.max(height(node.left),height(node.right));
        x.height=1+Math.max(height(x.left),height(x.right));
        return x;
    }
    public AVLNode RR(AVLNode node){
        AVLNode x=node.left;
        AVLNode tmp=x.right;

        node.left=tmp;
        x.right=node;

        node.height=1+Math.max(height(node.left),height(node.right));
        x.height=1+Math.max(height(x.left),height(x.right));
        return x;
    }
    public AVLNode RL(AVLNode node){
        node.right=RR(node.right);
        return LL(node);
    }
    public AVLNode LR(AVLNode node){
        node.left=LL(node.left);
        return RR(node);
    }
    public AVLNode insert(AVLNode node, int key){
        if (node==null){
            return new AVLNode(key);
        }
        if (key>node.value){
            node.right=insert(node.right,key);
        }else if (key<node.value){
            node.left=insert(node.left,key);
        }else{
            System.out.println("duplicate value!");
            return node;
        }
        node.height=1+Math.max(height(node.left),height(node.right));

        int balance=balance(node);

        if (balance>1&&key>node.left.value){
            return LR(node);
        }
        if (balance>1&&key<node.left.value){
            return RR(node);
        }
        if (balance<-1&&key>node.right.value){
            return LL(node);
        }
        if (balance<-1&&key<node.right.value){
            return RL(node);
        }
        return node;
    }
    public AVLNode minNode(AVLNode node){
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }
    public AVLNode delete(AVLNode node, int key){
        if (node==null){
            return null;
        }
        if (key>node.value){
            node.right=delete(node.right,key);
        }else if (key<node.value){
            node.left=delete(node.left,key);
        }else{
            if (node.left==null&&node.right==null){
                node=null;
            }else if (node.left==null){
                node=node.right;
            }else if (node.right==null){
                node=node.left;
            }else{
                AVLNode minNode=minNode(node.right);
                node.value=minNode.value;
                node.right=delete(node.right,minNode.value);
            }
        }
        if (node==null){
            return node;
        }
        node.height=1+Math.max(height(node.left),height(node.right));
        int balance=balance(node);

        /*删除怎么旋转*/
        if (balance>1&&balance(node.left)>0){
            return RR(node);
        }
        if (balance>1&&balance(node.left)<0){
            return LR(node);
        }
        if (balance<-1&&balance(node.right)<0){
            return LL(node);
        }
        if (balance<-1&&balance(node.right)>0){
            return RL(node);
        }
        return node;
    }
    public void visit(AVLNode node){
        System.out.print(node.value+",");
    }
    public void inOrderStack(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        AVLNode current=node;
        while (current!=null||!stack.empty()){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
            current = stack.pop();
            visit(current);
            current = current.right;
        }
    }

    public static void main(String[] args) {
        AVLV9Tree tree=new AVLV9Tree();
        for (int i=0;i<100;i++){
            tree.root=tree.insert(tree.root,i);
        }
        tree.inOrderStack(tree.root);
        System.out.println();
        tree.postOrderStack(tree.root);
        System.out.println();
        tree.preOrderStack(tree.root);
        System.out.println();
        tree.root=tree.delete(tree.root,33);
        tree.root=tree.delete(tree.root,47);
        tree.inOrderStack(tree.root);
        System.out.println();
        tree.postOrderStack(tree.root);
        System.out.println();
        tree.preOrderStack(tree.root);

    }
    public void postOrderStack(AVLNode node){
        if (node==null)return;
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode current=node,prev=null;
        while (!stack.empty()){
            current=stack.peek();
            if ((current.left==null&&current.right==null)||
                    (current.left==prev||current.right==prev)){
                visit(current);
                stack.pop();
                prev=current;
            }else{
                if (current.right!=null){
                    stack.push(current.right);
                }
                if (current.left!=null){
                    stack.push(current.left);
                }
            }
        }
    }
    public void preOrderStack(AVLNode node){
        if (node==null)return;
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode current=node;
        while (!stack.empty()){
            current=stack.pop();
            visit(current);
            if (current.right!=null){
                stack.push(current.right);
            }
            if (current.left!=null){
                stack.push(current.left);
            }
        }
    }
}
