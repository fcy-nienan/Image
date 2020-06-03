package tree.AVL.V4;

import tree.AVLNode;

import java.util.BitSet;
import java.util.Random;

public class AVLV4Tree {
    private AVLNode root;
    public int height(AVLNode node){
        return node==null?0:node.getHeight();
    }
    public int balance(AVLNode node){
        return node==null?0:(height(node.getLeft())-height(node.getRight()));
    }
    public AVLNode LL(AVLNode node){
        AVLNode newRoot=node.getRight();
        AVLNode tmp=newRoot.getLeft();

        node.setRight(tmp);
        newRoot.setLeft(node);

        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));
        newRoot.setHeight(1+Math.max(height(newRoot.getLeft()),height(newRoot.getRight())));
        return newRoot;
    }
    public AVLNode RR(AVLNode node){
        AVLNode newRoot=node.getLeft();
        AVLNode tmp=newRoot.getRight();

        node.setLeft(tmp);
        newRoot.setRight(node);

        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));
        newRoot.setHeight(1+Math.max(height(newRoot.getLeft()),height(newRoot.getRight())));
        return newRoot;
    }
    public AVLNode insert(AVLNode node, int value){
        if (node==null){
            return new AVLNode(value);
        }
        if (value>node.getValue()){
            node.setRight(insert(node.getRight(),value));
        }else if (value<node.getValue()){
            node.setLeft(insert(node.getLeft(),value));
        }else{
            System.out.println("forbidden duplicate value!");
            return node;
        }

        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));

        int balance=balance(node);

        if (balance > 1 && value < node.getLeft().getValue()){
            return RR(node);
        }

        if (balance > 1 && value> node.getLeft().getValue()){
            node.setLeft(LL(node.getLeft()));
            return RR(node);
        }

        if (balance < -1 && value> node.getRight().getValue()){
            return LL(node);
        }

        if (balance < -1 && value < node.getRight().getValue()){
            node.setRight(RR(node.getRight()));
            return LL(node);
        }

        return node;

    }
    public static void main (String args[]) {
        DemoRemove();
    }
    public static void DemoRemove(){
        AVLV4Tree tree=new AVLV4Tree();
        BitSet set=new BitSet();
        int value=0;
        Random random=new Random();
        for (int i=0;i<15;i++){
            while (set.get(value)){
                value=random.nextInt(15);
            }
            set.set(value);
            tree.root=tree.insert(tree.root,value);
        }
//        tree.inOrder(tree.root);
        tree.root=tree.remove(tree.root,10);
        tree.inOrder(tree.root);
    }
    public static void DemoInsert(){
        AVLV4Tree tree=new AVLV4Tree();
        int value=0;
        Random random=new Random();
        BitSet bitSet=new BitSet();

        for (int i=0;i<1000;i++){
            while (bitSet.get(value)){
                value=random.nextInt(1000);
            }
            bitSet.set(value);
            tree.root=tree.insert(tree.root,value);
        }
        tree.inOrder(tree.root);
    }
    private AVLNode minValue(AVLNode node) {
        while (node.getLeft()!=null){
            node=node.getLeft();
        }
        return node;
    }
//    删除一个节点需要找到该节点的前驱节点
//    然后如何维持平衡呢?
//    删除后用哪个节点代替呢?
//    递归删除节点
//
    public AVLNode remove(AVLNode node, int value){
        if (node==null){
            return node;
        }
        if (value>node.getValue()){
            node.setRight(remove(node.getRight(),value));
        }else if (value<node.getValue()){
            node.setLeft(remove(node.getLeft(),value));
        }else{
            if (node.getLeft()==null&&node.getRight()==null){
                node=null;
            }else if (node.getLeft()==null){
                node=node.getRight();
//                node.setValue(node.getLeft().getValue());
//                node.setLeft(null);
            }else if (node.getRight()==null) {
                node = node.getLeft();
//                下面这种方式不行,如果右节点不是叶子节点的话那么就会丢失一部分节点
//                node.setValue(node.getRight().getValue());
//                node.setRight(null);
            }else{
                AVLNode tmp=minValue(node);
                node.setValue(tmp.getValue());

//                此时树中已经删除了需要删除的节点,但是tmp节点的值存在两份,一份当前节点,一份tmp原始节点
//                此时就相当于在当前节点的右节点中删除值为tmp值的节点,递归进行
                node.setRight(remove(node.getRight(),tmp.getValue()));
            }
        }
//        如果需要删除的节点是叶子节点,直接返回null
//        返回null就相当于置该节点为空,在上层函数栈中我们设置了node.setRight(returnValue)
//        此时返回null,就相当于把该节点的右(左)节点置为了空
//        之前我还疑问需要删除树中的一个节点应该获取该节点的父节点才对啊
        if (node==null){
            return node;
        }
//        更新节点高度
        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));

        int balance=balance(node);

        if (balance>1&&value<node.getLeft().getValue()){
            return RR(node);
        }
        if (balance>1&&value>node.getLeft().getValue()){
            node.setLeft(LL(node.getLeft()));
            return RR(node);
        }

        if (balance<-1&&value>node.getRight().getValue()){
            return LL(node);
        }
        if (balance<-1&&value<node.getRight().getValue()){
            node.setRight(RR(node.getRight()));
            return LL(node);
        }
        return node;

    }
    public void inOrder(AVLNode node){
        if (node!=null){
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }
}
