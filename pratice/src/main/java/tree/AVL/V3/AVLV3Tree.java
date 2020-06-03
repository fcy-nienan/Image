package tree.AVL.V3;

import tree.AVLNode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AVLV3Tree {
    private AVLNode root;
    public int getHeight(AVLNode node){
        if (node==null){
            return 0;
        }
        return node.getHeight();
    }
    public int balance(AVLNode node){
        if (node==null){
            return 0;
        }
        return getHeight(node.getLeft())-getHeight(node.getRight());
    }
//    left rotation case
    public AVLNode LL(AVLNode node){
        AVLNode otherRoot=node.getRight();
        AVLNode tmp=otherRoot.getLeft();

        otherRoot.setLeft(node);
        node.setRight(tmp);

//        tmp.setHeight(N)
        node.setHeight(1+Math.max(getHeight(node.getLeft()),getHeight(node.getRight())));
        otherRoot.setHeight(1+Math.max(getHeight(otherRoot.getLeft()),getHeight(otherRoot.getRight())));
        return otherRoot;
    }
    public AVLNode RR(AVLNode node){
        AVLNode otherRoot=node.getLeft();
        AVLNode tmp=otherRoot.getRight();

        node.setLeft(tmp);
        otherRoot.setRight(node);

        node.setHeight(1+Math.max(getHeight(node.getLeft()),getHeight(node.getRight())));
        otherRoot.setHeight(1+Math.max(getHeight(otherRoot.getLeft()),getHeight(otherRoot.getRight())));
        return otherRoot;
    }
    public AVLNode LR(AVLNode node){
        node.setLeft(LL(node.getLeft()));
        return RR(node);
    }
    public AVLNode RL(AVLNode node){
        node.setRight(RR(node.getRight()));
        return LL(node);
    }
    public AVLNode insert(AVLNode root, int value){
        if (root==null){
            return new AVLNode(value);
        }
        if (value>root.getValue()){
            root.setRight(insert(root.getRight(),value));
        }else if (value<root.getValue()){
            root.setLeft(insert(root.getLeft(),value));
        }else{
            System.out.println("duplicate node value !");
            return root;
        }

//        新插入节点的所有上面的节点都需要高度+1

//        root.setHeight(root.getHeight()+1);
        root.setHeight(1+Math.max(getHeight(root.getLeft()),getHeight(root.getRight())));

        int balance=balance(root);
        if (balance>1&&value<root.getLeft().getValue()){
            return RR(root);
        }
        if (balance>1&&value>root.getLeft().getValue()){
            root.setLeft(LL(root.getLeft()));
            return RR(root);
        }
        if (balance<-1&&value>root.getRight().getValue()){
            return LL(root);
        }
        if (balance<-1&&value<root.getRight().getValue()){
            root.setRight(RR(root.getRight()));
            return LL(root);
        }
//        递归过程中不失衡的中间节点
        return root;
    }
    public void inOrder(AVLNode node){
        if (node!=null){
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }
    public static void main (String args[]) {
        AVLV3Tree tree=new AVLV3Tree();
        int value=0;
        Random random=new Random();
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<100;i++){
            while (set.contains(value)){
                value=random.nextInt(100);
            }
            set.add(value);
            tree.root=tree.insert(tree.root,value);
        }
        tree.inOrder(tree.root);
    }
}
