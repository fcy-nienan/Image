package com.fcy.DataStruct.Tree;
import java.util.LinkedList;
import java.util.Queue;
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
        System.out.println(value);
        TreeNode treeNode =create(value.toString());
        DFS(treeNode);
        System.out.println();
        BFS(treeNode);
    }
    public static void BFS(TreeNode root){
        Queue<TreeNode> queue=new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node=queue.element();
            System.out.print(node.getValue()+"--");
            queue.remove();
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }

    }
    public static void DFS(TreeNode root){
        Stack<TreeNode> stack=new Stack();
        stack.push(root);
        while (!stack.empty()){
            TreeNode node=stack.peek();
            System.out.print(node.getValue()+"--");
            stack.pop();
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }
    public static TreeNode create(String values){
        String[] strings=values.split(",");
        TreeNode treeNode =new TreeNode();
        create(treeNode,strings,1);
        return treeNode;
    }
    public static void create(TreeNode treeNode, Object[] object, int index){
        treeNode.setValue(object[index]);
        int left=2*index;
        int right=left+1;
        if (left<object.length){
            treeNode.left=new TreeNode();
            create(treeNode.left,object,left);
        }
        if(right<object.length){
            treeNode.right=new TreeNode();
            create(treeNode.right,object,right);
        }
    }
    public static void preOrder(TreeNode treeNode){
        if (treeNode !=null){
            System.out.print(treeNode.getValue()+",");
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }
    public static void inOrder(TreeNode treeNode){
        if (treeNode !=null){
            inOrder(treeNode.left);
            System.out.print(treeNode.getValue()+",");
            inOrder(treeNode.right);
        }
    }
    public static void postOrder(TreeNode treeNode){
        if (treeNode !=null){
            postOrder(treeNode.left);
            postOrder(treeNode.right);
            System.out.print(treeNode.getValue()+",");
        }
    }

















    public static void preStackOrder(TreeNode treeNode){
        System.out.print("非递归前序遍历:");
        Stack<TreeNode> stack=new Stack<>();
        stack.push(treeNode);
        while(!stack.empty()){
            TreeNode treeNode1 =stack.pop();
            System.out.print(treeNode1.getValue()+",");
            if (treeNode1.right!=null){
                stack.push(treeNode1.right);
            }
            if (treeNode1.left!=null){
                stack.push(treeNode1.left);
            }
        }
    }
    /**
    *@descripiton 先根节点入栈,然后一直根节点的左节点入栈
     * 然后已知栈的pop的元素是最左边的一个元素,将当前节点当作一个根节点,由上一步的左节点入栈已知该节点没有左节点
     * 所以直接输出该节点的值,然后获取该节点的右节点,右节点入栈,
     * 右节点入栈的时候又将该右节点当作一个根节点,然后根节点的左节点入栈,已知循环知道栈中没有元素
    */
    public static void inStackOrder(TreeNode t){
        System.out.print("非递归中序遍历:");
        Stack<TreeNode> s=new Stack<>();
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
    public static void postStackOrder(TreeNode treeNode){
        System.out.print("非递归后序遍历:");
        Stack<TreeNode> stack=new Stack<>();
        TreeNode parent;
        while (treeNode !=null||!stack.isEmpty()) {
            while (treeNode !=null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()){
                treeNode =stack.pop();
                System.out.print(treeNode.getValue()+",");
                if (!stack.isEmpty()) {
                    parent = stack.peek();
                    if (parent.right != treeNode)
                        treeNode = parent.right;
                    else
                        treeNode = null;
                }else{
                    treeNode =null;//这里两个null是重点,如果当前节点等于右节点则不入栈,如果栈为空则该节点不入栈
                }
            }
        }
    }
    public static void post(TreeNode tree){
        if (tree!=null){
            Stack<TreeNode> stack=new Stack<>();
            stack.push(tree);
            TreeNode pre=null;
            while (!stack.empty()){
                TreeNode node=stack.peek();
                if ((node.left==null&&node.right==null)||(pre!=null&&(pre==node.left||pre==node.right))){
                    System.out.println(node.getValue());
                    stack.pop();
                    pre=node;
                }else{
                    if (node.right!=null){
                        stack.push(node.right);
                    }
                    if (node.left!=null){
                        stack.push(node.left);
                    }
                }
            }
        }
    }
}
