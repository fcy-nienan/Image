package tree.AVL;

import tree.AVLNode;

public class AVLTree {
    private AVLNode root;

    public int getHeight(AVLNode root) {
        if (root != null) {
            if (root.getLeft() == null && root.getRight() == null) {
                return 1;
            }
            int left = getHeight(root.getLeft());
            int right = getHeight(root.getRight());
            return Math.max(left, right);
        } else {
            return 0;
        }
    }

    public void query(int value) {

    }

    public void delete(AVLNode node) {

    }
//    左旋

    public AVLNode LL(AVLNode node) {
        System.out.println("LL");
        AVLNode tmp = node.getRight();
        tmp.setRight(tmp.getLeft());
        tmp.setLeft(tmp);
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        tmp.setHeight(Math.max(getHeight(tmp.getLeft()), getHeight(tmp.getRight())) + 1);
        return tmp;
    }

    public AVLNode RR(AVLNode node) {
        System.out.println("RR");
        AVLNode tmp = node.getLeft();
        node.setLeft(tmp.getRight());
        tmp.setRight(node);
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        tmp.setHeight(Math.max(getHeight(tmp.getLeft()), getHeight(tmp.getRight())) + 1);
        return tmp;
    }

    public AVLNode LR(AVLNode node) {
        node.setLeft(LL(node.getLeft()));
        return RR(node);
    }

    public AVLNode RL(AVLNode node) {
        node.setRight(RR(node.getRight()));
        return LL(node);
    }

    public int min() {
        AVLNode node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    public int max() {
        AVLNode node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    public AVLNode search(int value) {
        AVLNode node = root;
        while (node != null) {
            if (value > node.getValue()) {
                node = node.getLeft();
            } else if (value < node.getValue()) {
                node = node.getRight();
            } else {
                return node;
            }
        }
        return null;
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    public void insert1(int value) {
        root = insert(root, value);
    }

    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    public AVLNode insert(AVLNode node, int key) {
        /* 1. Perform the normal BST insertion */
        if (node == null) {
            AVLNode avlNode = new AVLNode(key);
            avlNode.setHeight(1);
            return avlNode;
        }
        if (key < node.getValue())
            node.setLeft(insert(node.getLeft(), key));
        else if (key > node.getValue())
            node.setRight(insert(node.getRight(), key));
        else // Equal keys are not allowed in BST
            return node;

        /* 2. Update height of this ancestor node */
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    /* 3. Get the balance factor of this ancestor
        node to check whether this node became
        unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then
        // there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.getLeft().getValue())
            return RR(node);

        // Right Right Case
        if (balance < -1 && key > node.getRight().getValue())
            return LR(node);

        // Left Right Case
        if (balance > 1 && key > node.getLeft().getValue()) {
            return LR(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.getRight().getValue()) {
            return RL(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    private AVLNode insert(int value, AVLNode node) {
        if (node == null) {
            node = new AVLNode(value);
            return node;
        }
        if (value > node.getValue()) {
            node.setRight(insert(value, node.getRight()));
            int balance = Math.abs(getHeight(node.getRight()) - getHeight(node.getLeft()));
            if (balance > 1) {
                if (value < node.getRight().getValue()) {
                    node = RL(node);
                } else {
                    node = LL(node);
                }
            }
        } else if (value < node.getValue()) {
            node.setLeft(insert(value, node.getLeft()));
            int balance = Math.abs(getHeight(node.getRight()) - getHeight(node.getLeft()));
            if (balance > 1) {
                if (value > node.getLeft().getValue()) {
                    node = LR(node);
                } else {
                    node = RR(node);
                }
            }
        } else {
            return node;
        }
        int h = Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
        System.out.println("height:" + h);
        node.setHeight(h);
        return node;
    }

    public String toString() {
        return root.toString();
    }
}
