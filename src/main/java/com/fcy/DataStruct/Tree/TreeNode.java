package com.fcy.DataStruct.Tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-15  22:19
 */
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    private T value;
    public TreeNode(T t){
        this.value=t;
    }
    public String toString(){
        return value.toString();
    }
    public T getValue(){
        return value;
    }
    public void setValue(T t){
        this.value=t;
    }
    public void setLeft(TreeNode<T> left){
        this.left=left;
    }
    public TreeNode<T> getLeft(){
        return this.right;
    }
    public void setRight(TreeNode<T> right){
        this.left=right;
    }
    public TreeNode<T> getRight(){
        return this.right;
    }
}
