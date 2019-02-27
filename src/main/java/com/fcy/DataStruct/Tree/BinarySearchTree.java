package com.fcy.DataStruct.Tree;

import java.util.Stack;

/**
 * @descripiton:                                4
 *                                  2                                8
 *                             1             3              6                9
 *                         0                           5        7
 *                    4 2 1 0 3 8 6 5 7 9
 *
 *
 * @author: fcy
 * @date: 2019-02-24  12:52
 */
public class BinarySearchTree {
    private TreeNode<Integer> root;

    public static void main(String[] args) {
        BinarySearchTree tree=new BinarySearchTree();
        int[] data=new int[]{4,8,2,6,3,9,1,0,5,7};
        for(int i:data){
            tree.insert(i);
        }
        System.out.println();
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.remove(4);
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.InOrderStack(tree.getRoot());
    }
    public TreeNode getRoot(){
        return root;
    }
    public void insert(int value){
        if (root==null){
            root=new TreeNode(value);
        }else{
            TreeNode<Integer> node=root;
            while (node!=null){
                if (value>node.getValue()){
                    if (node.right==null){
                        node.right=new TreeNode<>(value);
                        return;
                    }else{
                        node=node.right;
                    }
                }else if(value<node.getValue()){
                    if (node.left==null){
                        node.left=new TreeNode<>(value);
                        return;
                    }else{
                        node=node.left;
                    }
                }else{
                    System.out.println("二叉树已存在该值!");
                    return;
                }
            }
        }
    }
    public void remove(int value){
        TreeNode<Integer> node=root,parent=root;
        while (node!=null){
            if (value>node.getValue()){
                parent=node;
                node=parent.right;
            }else if(value<node.getValue()){
                parent=node;
                node=parent.left;
            }else{//如果找到了
                if (node.left==null&&node.right==null){//如果是叶子节点
                    if (node==parent.left){
                        parent.left=null;
                    }else{
                        parent.right=null;
                    }
                    return;
                }else if(node.left==null){//如果左节点为空
                    if (node==parent.left){
                        parent.left=node.right;
                    }else if(node==parent.right){
                        parent.right=node.right;
                    }
                    return;
                }else if(node.right==null){//如果右节点为空
                    if (node==parent.left){
                        parent.left=node.left;
                    }else if(node==parent.right){
                        parent.right=node.left;
                    }
                    return;
                }else{//左右节点都不为空
                    TreeNode predecessor=node.left;
                    TreeNode par=predecessor;
                    while (predecessor.right!=null){
                        par=predecessor;
                        predecessor=predecessor.right;
                    }
                    if (predecessor!=node.left){
                        par.right=predecessor.left;
                        predecessor.left=node.left;
                    }
                    if (root==node){
                        root=predecessor;
                    }else if (parent.left==node){
                        parent.left=predecessor;
                    }else{
                        parent.right=predecessor;
                    }
                    predecessor.right=node.right;
                    return;
                }
            }
        }
    }
    public void preOrder(TreeNode node){
       if (node!=null){
           System.out.print(node+",");
           preOrder(node.left);
           preOrder(node.right);
       }
    }
    public void inOrder(TreeNode node){
        if (node!=null){
            inOrder(node.left);
            System.out.println(node.getValue());
            inOrder(node.right);
        }
    }
    public void postOrder(TreeNode node){
        if (node!=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.getValue());
        }
    }
    public TreeNode find(int value){
        TreeNode<Integer> node=root;
        while (node!=null){
            if (value>node.getValue()){
                node=node.right;
            }else if(value<node.getValue()){
                node=node.left;
            }else{
                return node;
            }
        }
        return null;
    }
    public void PreOrderStack(TreeNode node){
        Stack<TreeNode> stack=new Stack();
        stack.push(node);
        while (!stack.empty()){
            TreeNode current=stack.pop();
            System.out.println(current.getValue());
            if (current.right!=null)
                stack.push(current.right);
            if (current.left!=null)
                stack.push(current.left);
        }
    }
    public void InOrderStack(TreeNode node){
        Stack<TreeNode> stack=new Stack<>();
        while (node!=null){
            stack.push(node);
            node=node.left;
        }
        while (!stack.empty()){
            TreeNode current=stack.pop();
            System.out.println(current.getValue());
            current = current.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }
    }
//    非递归后续遍历
    public void PostOrderStack(TreeNode node){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(node);
        TreeNode pre=null;
        while (!stack.empty()){
            TreeNode current=stack.peek();
//            一个节点啥时候才能被访问呢?
//            1.当它的左节点为空并且右节点为空的时候可以直接访问
//            2.当上一次访问的节点是它的右节点时
//            3.当它的左右节点有一个为空时,并且上一个访问的节点时它的左节点或者右节点时
//            其中第二,三种可以重叠到一起
//            因为入栈的时候是先入右节点再入左节点,并且为空不入栈
//            所以如果一个节点存在子节点的话上一个节点访问的一定是左节点或者右节点
//            所以可以直接通过pre==current.left||pre==current.right判断是否可以访问当前节点
//            另外第一次push的时候pre==null,所以还需要判断一下pre!=null

            if ((current.left==null&&current.right==null)||((pre!=null&&(pre==current.left||pre==current.right)))){
                current=stack.pop();
                System.out.println(current);
                pre=current;
            }else{
                if (current.right!=null)
                    stack.push(current.right);
                if (current.left!=null)
                    stack.push(current.left);
            }
        }
    }
}
