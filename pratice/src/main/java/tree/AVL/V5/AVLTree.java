package tree.AVL.V5;

import tree.AVLNode;

import java.util.BitSet;
import java.util.Random;
import java.util.Stack;

public class AVLTree {
    AVLNode root;

    // A utility function to get height of the tree
    int height(AVLNode N)
    {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    AVLNode rightRotate(AVLNode y)
    {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    AVLNode leftRotate(AVLNode x)
    {
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
    int getBalance(AVLNode N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    AVLNode insert(AVLNode AVLNode, int value)
    {
        /* 1. Perform the normal BST rotation */
        if (AVLNode == null)
            return (new AVLNode(value));

        if (value < AVLNode.value)
            AVLNode.left = insert(AVLNode.left, value);
        else if (value > AVLNode.value)
            AVLNode.right = insert(AVLNode.right, value);
        else // Equal values not allowed
            return AVLNode;

        /* 2. Update height of this ancestor AVLNode */
        AVLNode.height = 1 + max(height(AVLNode.left),
                height(AVLNode.right));

        /* 3. Get the balance factor of this ancestor
        AVLNode to check whether this AVLNode became
        Wunbalanced */
        int balance = getBalance(AVLNode);

        // If this AVLNode becomes unbalanced, then
        // there are 4 cases Left Left Case
        if (balance > 1 && value < AVLNode.left.value)
            return rightRotate(AVLNode);

        // Right Right Case
        if (balance < -1 && value > AVLNode.right.value)
            return leftRotate(AVLNode);

        // Left Right Case
        if (balance > 1 && value > AVLNode.left.value)
        {
            AVLNode.left = leftRotate(AVLNode.left);
            return rightRotate(AVLNode);
        }

        // Right Left Case
        if (balance < -1 && value < AVLNode.right.value)
        {
            AVLNode.right = rightRotate(AVLNode.right);
            return leftRotate(AVLNode);
        }

        /* return the (unchanged) AVLNode pointer */
        return AVLNode;
    }

    /* Given a non-empty binary search tree, return the
    AVLNode with minimum value value found in that tree.
    Note that the entire tree does not need to be
    searched. */
    AVLNode minValueAVLNode(AVLNode AVLNode) {
        tree.AVLNode current = AVLNode;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }


    public AVLNode deleteAVLNode1(AVLNode root, int value){
        if (root==null){
            return root;
        }
        if (value>root.value){
            root.right=deleteAVLNode1(root.right,value);
        }else if (value<root.value){
            root.left=deleteAVLNode1(root.left,value);
        }else{
//            删除该值
            if (root.left==null&&root.right==null){
                root=null;
            }else if (root.left==null){
                root=root.left;
            }else if (root.right==null){
                root=root.right;
            }else{
                AVLNode tmp=minValueAVLNode(root);
                root.value=tmp.value;
//                root=tmp;
//                tmp节点可能有右节点
                root.right=deleteAVLNode1(root.right,tmp.value);
            }
        }
        if (root==null){
            return root;
        }

        root.height=1+Math.max(height(root.left),height(root.right));

        int balance=getBalance(root);

//        主要看第一个balance,再结合第二个balance观察节点的失衡状态
//        这个=0放在上面和下面都可以吗?
        if (balance>1&&getBalance(root.left)>=0){
            return rightRotate(root);
        }
        if (balance>1&&getBalance(root.left)<0){
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance<-1&&getBalance(root.right)<=0){
            return leftRotate(root);
        }
        if (balance<-1&&getBalance(root.right)>=0){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;

    }


//    在root节点开始查找value值,并且返回一个删除值后并且旋转后平衡的根节点
    AVLNode deleteAVLNode(AVLNode root, int value) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the value to be deleted is smaller than
        // the root's value, then it lies in left subtree
        if (value < root.value)
            root.left = deleteAVLNode(root.left, value);

            // If the value to be deleted is greater than the
            // root's value, then it lies in right subtree
        else if (value > root.value)
            root.right = deleteAVLNode(root.right, value);

            // if value is same as root's value, then this is the AVLNode
            // to be deleted
        else
        {

            // AVLNode with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // AVLNode with two children: Get the inorder
                // successor (smallest in the right subtree)
                AVLNode temp = minValueAVLNode(root.right);

                // Copy the inorder successor's data to this AVLNode
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteAVLNode(root.right, temp.value);
            }
        }

        // If the tree had only one AVLNode then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT AVLNode
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS AVLNode (to check whether
        // this AVLNode became unbalanced)
        int balance = getBalance(root);

        // If this AVLNode becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) > 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) <= 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) < 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) >= 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // A utility function to print preorder traversal of
    // the tree. The function also prints height of every
    // AVLNode
    void preOrder(AVLNode AVLNode) {
        if (AVLNode != null)
        {
            System.out.print(AVLNode.value + " ");
            preOrder(AVLNode.left);
            preOrder(AVLNode.right);
        }
    }

    public static void main(String[] args)
    {
        AVLTree tree = new AVLTree();


        /* Constructing tree given in the above figure */
        int value=0;
        Random random=new Random();
        BitSet set=new BitSet();

        for (int i=0;i<100;i++){
            while (set.get(value)){
                value=random.nextInt(100);
            }
            set.set(value);
            tree.root=tree.insert(tree.root,value);
        }
        tree.root = tree.deleteAVLNode(tree.root, 5);
        for (int i=0;i<10;i++){
            int v=random.nextInt(100);
            tree.root=tree.deleteAVLNode(tree.root,v);
            System.out.println("delete value :"+v);
        }
        System.out.println("Preorder traversal after "+
                "deletion of 10 :");
        tree.preOrderStack(tree.root);
        System.out.println();
        tree.preOrderStack1(tree.root);
        System.out.println();
        System.out.println(tree.isBalance(tree.root));
    }
    public boolean isBalance(AVLNode AVLNode){
        if (AVLNode!=null){
            boolean flag=true;
            if (getBalance(AVLNode)>1||getBalance(AVLNode)<-1){
                flag=false;
            }
            boolean left=isBalance(AVLNode.left);
            boolean right=isBalance(AVLNode.right);
            return flag&left&right;
        }
        return true;
    }
    public void inOrder(AVLNode AVLNode){
        if (AVLNode!=null){
            inOrder(AVLNode.left);
            System.out.print(AVLNode.value+"  ");
            inOrder(AVLNode.right);
        }
    }
//    非递归中序遍历,对于从栈中拿出的每一个节点我们可以认为其左孩子已经访问了的
//    所以不需要处理怎么标识一个节点是否已经被访问过了
    public void inOrderStack1(AVLNode AVLNode){
        Stack<tree.AVLNode> stack=new Stack<>();
        tree.AVLNode current=AVLNode;
        while (true){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
//            需要判断栈是否为空再从其拿元素
            if (stack.empty())return;
            current=stack.pop();
            System.out.println(current.value);
            current=current.right;

        }
    }
    public void preOrderStack1(AVLNode AVLNode){
        if (AVLNode==null)return;
        Stack<tree.AVLNode> stack=new Stack<>();
        tree.AVLNode current=AVLNode;
        while (true){
            while (current!=null){
                System.out.print(current.value+"  ");
                stack.push(current);
                current=current.left;
            }
            if (!stack.empty()){
                current=stack.pop();
                current=current.right;
            }else{
                return;
            }
        }
    }
    public void preOrderStack(AVLNode AVLNode){
        if (AVLNode==null)return;
        Stack<tree.AVLNode> stack=new Stack<>();
        tree.AVLNode current=AVLNode;
        stack.push(current);
        while (true){
            if (stack.empty())return;
            current=stack.pop();
            if (current.right!=null){
                stack.push(current.right);
            }
            if (current.left!=null){
                stack.push(current.left);
            }
            System.out.print(current.value+"  ");
        }
    }
//    非递归中序遍历如何判断一个节点是否访问过呢?
//    下面的算法就思路来说是正确的,但是无法标识一个节点是否被访问过了,所以无法输出正确结果,会死循环,因为访问过的节点会重复访问
    public void inOrderStack(AVLNode AVLNode){
        Stack<tree.AVLNode> stack=new Stack<tree.AVLNode>();
        stack.push(AVLNode);
        while (true){
            if (stack.empty()){
                return;
            }
            tree.AVLNode current=stack.pop();
            if (current.right!=null){
                stack.push(current.right);
            }
            if (current.left==null){
                System.out.print(current.value+"  ");
            }else{
                stack.push(current);
                stack.push(current.left);
            }
        }
    }
}
