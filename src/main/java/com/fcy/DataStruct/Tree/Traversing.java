package com.fcy.DataStruct.Tree;
import java.util.Stack;
/**
 * @descripiton:
 * 二叉树
 *  树的高度    从叶子节点往上数
 *  树的深度    从根节点往下数
 *  树的层数    根节点的层数为1
 *  子节点数    等于层数^2-1
 *  完全二叉树
 *  满二叉树
 *  二叉查找树
 *                             1
 *                    2              3
 *             4          5       6       7
 *          8      9  10
 */
public class Traversing {
    public static void main(String[] args) {
        int valueCount=40;
        StringBuilder value=new StringBuilder();
        for(int i=0;i<valueCount;i++){
            value.append(i+",");
        }
        value.deleteCharAt(value.length()-1);
        Tree tree=create(value.toString());
        preOrder(tree);
        System.out.println();
        inOrder(tree);
        System.out.println();
        postOrder(tree);
        System.out.println();
        preStackOrder(tree);
        System.out.println();
        inStackOrder(tree);
        System.out.println();
        postStackOrder(tree);
        System.out.println();
    }
    public static void tReference(Tree tree){
        tree=new Tree(100);
//        System.out.println(tree.left);
//        System.out.println(tree.right);
//        tree.left=tree.right;
    }
    public static Tree create(String values){
        String[] strings=values.split(",");
        Tree tree=new Tree();
        create(tree,strings,1);
        return tree;
    }
    public static void create(Tree tree,Object[] object,int index){
        tree.setValue(object[index]);
        int left=2*index;
        int right=left+1;
        if (left<object.length){
            tree.left=new Tree();
            create(tree.left,object,left);
        }
        if(right<object.length){
            tree.right=new Tree();
            create(tree.right,object,right);
        }
    }
    public static void preOrder(Tree tree){
        if (tree!=null){
            System.out.print(tree.getValue()+",");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }
    public static void inOrder(Tree tree){
        if (tree!=null){
            inOrder(tree.left);
            System.out.print(tree.getValue()+",");
            inOrder(tree.right);
        }
    }
    public static void postOrder(Tree tree){
        if (tree!=null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.getValue()+",");
        }
    }
    public static void preStackOrder(Tree tree){
        System.out.print("非递归前序遍历:");
        Stack<Tree> stack=new Stack<>();
        stack.push(tree);
        while(!stack.empty()){
            Tree tree1=stack.pop();
            System.out.print(tree1.getValue()+",");
            if (tree1.right!=null){
                stack.push(tree1.right);
            }
            if (tree1.left!=null){
                stack.push(tree1.left);
            }
        }
    }
    /**
    *@descripiton 先根节点入栈,然后一直根节点的左节点入栈
     * 然后已知栈的pop的元素是最左边的一个元素,将当前节点当作一个根节点,由上一步的左节点入栈已知该节点没有左节点
     * 所以直接输出该节点的值,然后获取该节点的右节点,右节点入栈,
     * 右节点入栈的时候又将该右节点当作一个根节点,然后根节点的左节点入栈,已知循环知道栈中没有元素
    */
    public static void inStackOrder(Tree t){
        System.out.print("非递归中序遍历:");
        Stack<Tree> s=new Stack<>();
        while (t!=null||!s.isEmpty()){
            while (t!=null){
                s.push(t);
                t=t.left;
            }
            if (!s.isEmpty()){
                t=s.pop();
                System.out.print(t.getValue()+",");
                t=t.right;
            }
        }
    }
    /**
    *@descripiton 通过记录当前节点的父节点,比较当前节点的值是否为右孩子,如果是则不入栈,如果是则将当前节点的值变为右孩子
    */
    public static void postStackOrder(Tree tree){
        System.out.print("非递归后序遍历:");
        Stack<Tree> stack=new Stack<>();
        Tree parent;
        while (tree!=null||!stack.isEmpty()) {
            while (tree!=null){
                stack.push(tree);
                tree=tree.left;
            }
            if (!stack.isEmpty()){
                tree=stack.pop();
                System.out.print(tree.getValue()+",");
                if (!stack.isEmpty()) {
                    parent = stack.peek();
                    if (parent.right != tree)
                        tree = parent.right;
                    else
                        tree = null;
                }else{
                    tree=null;//这里两个null是重点,如果当前节点等于右节点则不入栈,如果栈为空则该节点不入栈
                }
            }
        }
    }
}
