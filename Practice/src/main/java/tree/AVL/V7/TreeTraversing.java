package tree.AVL.V7;

import tree.AVLNode;

import java.util.Stack;

public class TreeTraversing {
    public static void main (String args[]) {

    }
    public static void visit(AVLNode node){
        System.out.print(node.value+"  ");
    }
    public static void preOrderStack(AVLNode node){
        if(node==null)
            return;
        Stack<AVLNode> stack=new Stack();
        stack.push(node);
        AVLNode current;
        while(!stack.empty()){
            current=stack.pop();
            visit(current);
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
    }
    public static void inOrderStack(AVLNode node){
        Stack<AVLNode> stack=new Stack();
        AVLNode current=node;
        while(current!=null||!stack.empty()){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            visit(current);
            current=current.right;
        }
    }
    public static void postOrderStack(AVLNode node){
        if(node==null){
            return;
        }

        Stack<AVLNode> stack=new Stack();
        stack.push(node);
        Stack<AVLNode> other=new Stack();
        AVLNode current=node;
        while(!stack.empty()){
            current=stack.pop();
            other.push(current);
            if(current.left!=null){
                stack.push(current.left);
            }
            if(current.right!=null){
                stack.push(current.right);
            }
        }
        while(!other.empty()){
            current=other.pop();
            visit(current);
        }
    }
    public static void postOrderStack1(AVLNode node){
        if(node==null)
            return;
        Stack<AVLNode> stack=new Stack();
        stack.push(node);
        AVLNode current=node,prev=null;
        while(!stack.empty()){
            current=stack.peek();
            //prev==current.left||prev==current.right是表示当前节点只有一个左孩子或者一个右孩子

            //如果一个节点有两个孩子,栈中的元素是这样的root-right-left
            //访问left的时候符合左右节点为null的情况,然后prev==left
            //然后再次出栈的是右节点,此时符合左右节点都为null的情况,prev=left
            //这个prev==null,没必要,可以去掉
            //push进的节点不可能为null,那么current也不可能为null
            //那么prev也不可能为null
            if((current.left==null&&current.right==null)||
                    ((prev!=null)&&(prev==current.left||prev==current.right))){
                visit(current);
                stack.pop();
                prev=current;
            }else{
                if(current.right!=null){
                    stack.push(current.right);
                }
                if(current.left!=null){
                    stack.push(current.left);
                }
            }
        }
    }
}
