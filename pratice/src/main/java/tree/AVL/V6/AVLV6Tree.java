package tree.AVL.V6;

import tree.AVL.V7.TreeTraversing;
import tree.AVLNode;

import java.util.BitSet;
import java.util.Random;
import java.util.Stack;

/*
* 错误地方:
* 删除节点的时候当被删除节点有两个孩子的时候和当前节点替换值的节点是当前节点右孩子的最小值
* 高度赋值一定要加一
*
* */
public class AVLV6Tree {
    private AVLNode root;
    public int height(AVLNode node){
        if (node!=null){
            return node.height;
        }
        return 0;
    }
    public int balance(AVLNode node){
        if (node!=null){
            return height(node.left)-height(node.right);
        }
        return 0;
    }
    public AVLNode LL(AVLNode x){
        AVLNode y=x.right;
        AVLNode t=y.left;

        y.left=x;
        x.right=t;


        x.height=1+Math.max(height(x.left),height(x.right));
        y.height=1+Math.max(height(y.left),height(y.right));
        return y;
    }
    void display(AVLNode node){
        if (node!=null){
            System.out.print(node.value);
            if (node.left!=null){
                System.out.print("(");
                display(node.left);
            }
            if (node.right!=null){
                System.out.print(",");
                display(node.right);
                System.out.print(")\r\n");
            }
        }

    }
    public AVLNode RR(AVLNode y){
        AVLNode x=y.left;
        AVLNode t2=x.right;

        /*这两个赋值顺序无所谓*/
        y.left=t2;
        x.right=y;

        /*height一定要加一*/
        y.height=1+Math.max(height(y.left),height(y.right));
        x.height=1+Math.max(height(x.left),height(x.right));
        return x;
    }
    public AVLNode LR(AVLNode node){
        node.left=LL(node.left);
        return RR(node);
    }
    public AVLNode RL(AVLNode node){
        node.right=RR(node.right);
        return LL(node);
    }
    private AVLNode insert(AVLNode node, int value){
        if (node==null){
            return new AVLNode(value);
        }
        if (value>node.value){
            node.right=insert(node.right,value);
        }else if (value<node.value){
            node.left=insert(node.left,value);
        }else {
            return node;
        }
        node.height=1+Math.max(height(node.left),height(node.right));

        int balance=balance(node);

        if (balance>1&&value<node.left.value){
            return RR(node);
        }
        if (balance>1&&value>node.left.value){
            return LR(node);
        }
        if (balance<-1&&value>node.right.value){
            return LL(node);
        }
        if (balance<-1&&value<node.right.value){
            return RL(node);
        }
        return node;
    }
    public void insert(int value){
        this.root=insert(this.root,value);
    }
    private AVLNode minNode(AVLNode node){
        while (node!=null&&node.left!=null){
            node=node.left;
        }
        return node;
    }
    private AVLNode delete(AVLNode node, int value){
        if (node==null){
            return null;
        }
        if (value>node.value){
            node.right=delete(node.right,value);
        }else if (value<node.value){
            node.left=delete(node.left,value);
        }else{
            if (node.left==null&&node.right==null){
                node=null;
            }else if (node.left==null){
                node=node.right;
            }else if (node.right==null){
                node=node.left;
            }else{
//                2020/1/28:找的是当前节点的右节点的最小值
//                AVLNode minNode=minNode(node);
                AVLNode minNode=minNode(node.right);
                node.value=minNode.value;

                node.right=delete(node.right,minNode.value);
            }
        }
        if (node==null){
            return null;
        }

        node.height=1+Math.max(height(node.left),height(node.right));

        int balance=balance(node);

        if (balance>1&&balance(node.left)>=0){
            return RR(node);
        }
        if (balance>1&&balance(node.left)<0){
            return LR(node);
        }
        if (balance<-1&&balance(node.right)<=0){
            return LL(node);
        }
        if (balance<-1&&balance(node.right)>0){
            return RL(node);
        }
        return node;
    }
    public void delete(int value){
        this.root=delete(this.root,value);
    }
    public void preOrder(AVLNode node){
        if (node!=null){
            System.out.print(node.value+"  ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public void inOrder(AVLNode node){
        if (node!=null){
            inOrder(node.left);
            System.out.print(node.value+"  ");
            inOrder(node.right);
        }
    }
    public void postOrder(AVLNode node){
        if (node!=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value+"  ");
        }
    }
    public void postOrderStack2(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode prev=null,current=node;
        while (!stack.empty()){
            current=stack.peek();
            if ((current.left==null&&current.right==null)||
                    (prev!=null)&&(current.left==prev||current.right==prev)){
                System.out.print(current.value+"  ");
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
    public void postOrderSimulatePreOrder(AVLNode node){
        /*
        * 先序遍历访问顺序是:根-左-右
        * 后续遍历访问顺序是:左-右-根
        * 我们先序遍历访问一个节点的时候将其压入另一个栈B
        * 然后栈B中的节点顺序是:右-左-根
        * 这个顺序还是不对,所以需要在正常先序遍历的时候先压入左节点,然后再压入右节点
        * 这时栈B中的节点顺序就变成了:左-右-根
        * 然后依次出栈就行了
        * */
        Stack<AVLNode> stack=new Stack<>();
        Stack<AVLNode> otherStack=new Stack<>();
        stack.push(node);
        while (!stack.empty()){
            AVLNode current=stack.pop();
            otherStack.push(current);
            if (current.left!=null){
                stack.push(current.left);
            }
            if (current.right!=null){
                stack.push(current.right);
            }
        }
        while (!otherStack.empty()){
            AVLNode otherNode=otherStack.pop();
            System.out.print(otherNode.value+"  ");
        }
    }
    public void postOrderStack1(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode current=node,pre=null;
        while (!stack.empty()){
            current=stack.peek();
            if ((current.left==null&&current.right==null)||
                    (pre!=null&&(pre==current.left||pre==current.right))){
                System.out.print(current.value+"  ");
                stack.pop();
                pre=current;
            }
        }
    }

    public void postOrderStack(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode current=node,pre=null;
        while (true) {
            if (stack.empty()) return;
            current = stack.peek();
            if (current.right == null && current.left == null ||
                    ((pre == current.left || pre == current.right))) {
                System.out.print(current.value + "  ");
                stack.pop();
                pre = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }
    public void preOrderStack(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        stack.push(node);
        AVLNode current;
        while (!stack.empty()){
            current=stack.pop();
            System.out.print(current.value+"  ");
            if (current.right!=null){
                stack.push(current.right);
            }
            if (current.left!=null){
                stack.push(current.left);
            }
        }
    }
    public void preOrderStackSimulateInOrder(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        AVLNode current=node;

        while (!stack.empty()||current!=null){
            while (current!=null){
                System.out.print(current.value+"  ");
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            current=current.right;
        }
    }
    public void inOrderStack(AVLNode node){
        Stack<AVLNode> stack=new Stack<>();
        AVLNode current=node;
        while (current!=null||!stack.empty()){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            System.out.print(current.value+"  ");
            current=current.right;
        }
    }
    public static void main (String[] args) {
        AVLV6Tree tree = new AVLV6Tree();
        Random random = new Random();
        BitSet set = new BitSet();
        int value = 0;
        int count = 100;
        for (int i = 0; i < count; i++) {
            while (set.get(value)) {
                value = random.nextInt(count);
            }
            set.set(value);
            tree.insert(value);
        }
        tree.preOrderStack(tree.root);
        System.out.println();
        TreeTraversing.preOrderStack(tree.root);
        System.out.println();
        TreeTraversing.inOrderStack(tree.root);
        System.out.println();
        TreeTraversing.postOrderStack(tree.root);
        System.out.println();
        TreeTraversing.postOrderStack1(tree.root);

    }
}
