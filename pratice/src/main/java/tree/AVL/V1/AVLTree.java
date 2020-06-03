package tree.AVL.V1;

import tree.AVLNode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AVLTree {

    AVLNode root;

    // A utility function to get the height of the tree
    int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
//    以y为root节点的二叉树
//    右旋转y节点,使得y的左节点成为一个当前的根结点,当前的二叉树保持平衡
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;
//      更新两个节点的高度,毫无疑问,只有这两个节点的高度变化了的
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
//    左旋
//    正常的旋转节点并且更新两个节点的高度
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of AVLNode N
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }
//    为啥没有LR旋转和RL旋转？

    AVLNode insert(AVLNode AVLNode, int value) {

        /* 1. Perform the normal BST insertion */
        if (AVLNode == null)
            return (new AVLNode(value));

        if (value < AVLNode.value)
            AVLNode.left = insert(AVLNode.left, value);
        else if (value > AVLNode.value)
            AVLNode.right = insert(AVLNode.right, value);
        else {// Duplicate values not allowed
            System.out.println("duplicate values !");
            return AVLNode;
        }

        /* 2. Update height of this ancestor AVLNode */
        AVLNode.height = 1 + max(height(AVLNode.left),
                height(AVLNode.right));

		/* 3. Get the balance factor of this ancestor
			AVLNode to check whether this AVLNode became
			unbalanced */
        int balance = getBalance(AVLNode);

        // If this AVLNode becomes unbalanced, then there
        // are 4 cases Left Left Case
//        当插入的节点大于需要旋转的左节点并且
//        当前节点的左节点高度-右节点高度>1
//        只需要右旋
        if (balance > 1 && value < AVLNode.left.value)
            return rightRotate(AVLNode);

//        getHeight(left)-getHeight(right)<-1
//        value>AVLNode.left.value
        if (balance < -1 && value > AVLNode.right.value)
            return leftRotate(AVLNode);

        // Left Right Case
//        getHeight(left)-getHeight(right)>1
//        value>AVLNode.left.value
        if (balance > 1 && value > AVLNode.left.value) {
            AVLNode.left = leftRotate(AVLNode.left);
            return rightRotate(AVLNode);
        }

        // Right Left Case
        if (balance < -1 && value < AVLNode.right.value) {
            AVLNode.right = rightRotate(AVLNode.right);
            return leftRotate(AVLNode);
        }

        /* return the (unchanged) AVLNode pointer */
        return AVLNode;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every AVLNode
    void preOrder(AVLNode AVLNode) {
        if (AVLNode != null) {
            System.out.print(AVLNode.value + " ");
            preOrder(AVLNode.left);
            preOrder(AVLNode.right);
        }
    }
    private static int size=0;
    void inOrder(AVLNode AVLNode){
        if (AVLNode!=null){
            inOrder(AVLNode.left);
            System.out.println(AVLNode.value+":"+(size++));
            inOrder(AVLNode.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        Random random=new Random();
        Set<Integer> set=new HashSet<>();
        int value=-1;
        for (int i=0;i<100;i++){
            do {
                value=random.nextInt(100);
            }while (set.contains(value));
            set.add(value);
            tree.root=tree.insert(tree.root,value);
        }
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.inOrder(tree.root);
        System.out.println(tree.root.value);
        System.out.println(tree.height(tree.root.left));
        System.out.println(tree.height(tree.root.right));
    }
}
// This code has been contributed by Mayank Jaiswal

