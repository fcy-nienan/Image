package com.fcy.DataStruct.Tree;

import java.util.HashMap;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-17  0:57
 */
public class TreeSearch {
    public static void main(String[] args) {
        HashMap map;
        TreeNode treeNode1 =new TreeNode();
        Random random=new Random();
        for(int i=1;i<6;i++){
            int value=random.nextInt(100);
            insert(treeNode1,value);
            System.out.print(value+",");
        }
//        insert(treeNode1,128);
//        System.out.println();
//        Traversing.preOrder(treeNode1);
//        System.out.println();
//        Traversing.inOrder(treeNode1);
//        System.out.println();
//        System.out.println(exist(treeNode1,128));
//        System.out.println(exist(treeNode1,56));
//        System.out.println(exist(treeNode1,23));
    }
    public static void insert(TreeNode root, int value){
        if (root.getValue()==null){//如果该节点的值为null,说明是创建第一个节点,直接赋值就可以了
            root.setValue(value);
            return;
        }
        TreeNode treeNode =root;
        TreeNode parent;
        int v=value;
        while (treeNode !=null){
            parent= treeNode;
            if ((int) treeNode.getValue()<v){//如果值大于当前节点的值
                treeNode = treeNode.right;//将当前节点变为当前节点的右孩子节点
                if (treeNode ==null){//如果右孩子为null,说明右孩子没有值,此时需要将该值赋予给它
                    parent.right=new TreeNode(value);
                }
            }else if((int) treeNode.getValue()>v){//如果值小于当前节点的值
                treeNode = treeNode.left;
                if (treeNode ==null){
                    parent.left=new TreeNode(value);
                }
            }else{//如果等于当前节点的值
                treeNode = treeNode.left;
                if (treeNode ==null){
                    parent.left=new TreeNode(value);
                }
            }
        }
    }
    /**
    *@descripiton 在二叉树中查找给定的值是否存在
     * 给定一个节点
     * 如果当前节点的值大于给定的节点,查找当前节点的左节点,表示该值应该可以在该节点的左边找到
     * 如果当前节点的值小于给定的节点,查找当前节点的右节点,表示该值应该可以在该节点的右边找到
     * 如果当前节点的值等于给定的节点,返回当前节点的值,表示在二叉树中找到了该值
     * 如果当前节点为null,说明没找到该值
     *
    */
    public static TreeNode find(TreeNode root, int value){
        if (root==null){
            System.out.println("查找的值:"+value+"不存在!");
            return null;
        }
        if (value<(int)root.getValue()){
            return find(root.left,value);
        }else if(value>(int)root.getValue()){
            return find(root.right,value);
        }else{
            return root;
        }
    }
    public static boolean exist(TreeNode root, int value){
        return find(root,value)==null?false:true;
    }
}
