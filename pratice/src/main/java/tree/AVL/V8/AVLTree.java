package tree.AVL.V8;

import tree.AVLNode;

import java.util.BitSet;
import java.util.Random;
import java.util.Stack;

public class AVLTree {
    private AVLNode root;

    public int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int balance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public AVLNode LL(AVLNode node) {
        AVLNode y = node.right;
        AVLNode t = y.left;

        node.right = t;
        y.left = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        y.height = 1 + Math.max(height(node.left), height(node.right));
        return y;
    }

    public AVLNode RR(AVLNode node) {
        AVLNode y = node.left;
        AVLNode t = y.right;

        node.left = t;
        y.right = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public AVLNode LR(AVLNode node) {
        node.left = LL(node.left);
        return RR(node);
    }

    public AVLNode RL(AVLNode node) {
        node.right = RR(node.right);
        return LL(node);
    }

    public AVLNode insert(AVLNode node, int value) {
        if (node == null) {
            return new AVLNode(value);
        }
        if (value > node.value) {
            node.right = insert(node.right, value);
        } else if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            return node;
        }
        node.setHeight(1 + Math.max(height(node.left), height(node.right)));
        int balance = balance(node);

        if (balance > 1 && value < node.left.value) {
            return RR(node);
        }
        if (balance > 1 && value > node.left.value) {
            return LR(node);
        }
        if (balance < -1 && value > node.right.value) {
            return LL(node);
        }
        if (balance < -1 && value < node.right.value) {
            return RL(node);
        }
        return node;
    }

    public AVLNode minNode(AVLNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public AVLNode deleteValue(AVLNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value > node.value) {
            node.right = deleteValue(node.right, value);
        } else if (value < node.value) {
            node.left = deleteValue(node.left, value);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode tmp = minNode(node.right);
                node.value = tmp.value;
                node.right = deleteValue(node.right, tmp.value);
            }
        }
        if (node == null) {
            return null;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balance(node);

        if (balance > 1 && balance(node.left) >= 0) {
            return RR(node);
        }
        if (balance > 1 && balance(node.left) < 0) {
            return LR(node);
        }
        if (balance < -1 && balance(node.right) >= 0) {
            return RL(node);
        }
        if (balance < -1 && balance(node.right) > 0) {
            return LL(node);
        }
        return node;
    }

    public void visit(AVLNode node) {
        System.out.print(node.value + "  ");
    }

    public void preOrderStack(AVLNode node) {
        if (node == null) return;
        Stack<AVLNode> stack = new Stack<>();
        stack.push(node);
        AVLNode current;
        while (!stack.empty()) {
            current = stack.pop();
            visit(current);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public void preOrderStackSimulateInOrder(AVLNode node) {
        Stack<AVLNode> stack = new Stack<>();
        AVLNode current = node;
        while (current != null || !stack.empty()) {
            while (current != null) {
                visit(current);
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            current = current.right;
        }
    }

    public void inOrderStack(AVLNode node) {
        Stack<AVLNode> stack = new Stack<>();
        AVLNode current = node;
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            visit(current);
            current = current.right;
        }

    }

    public void postOrderStack(AVLNode node) {
        if (node == null) return;
        Stack<AVLNode> stack = new Stack<>();
        stack.push(node);
        AVLNode current = node, prev = null;
        while (!stack.empty()) {
            current = stack.peek();
            if ((current.left == null && current.right == null) ||
                    (current.left == prev || current.right == prev)) {
                visit(current);
                stack.pop();
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }

    public static void main(String args[]) {
        AVLTree tree = new AVLTree();
        BitSet set = new BitSet();
        Random random = new Random();
        int value = 0;
        int count = 100;
        for (int i = 0; i < count; i++) {
            while (set.get(value)) {
                value = random.nextInt(count);
            }
            set.set(value);
            tree.root = tree.insert(tree.root, value);
        }
        tree.preOrderStack(tree.root);
        System.out.println();
        tree.preOrderStackSimulateInOrder(tree.root);
        System.out.println();
        tree.deleteValue(tree.root, 34);
        tree.deleteValue(tree.root, 12);
        tree.inOrderStack(tree.root);
        System.out.println();
        tree.postOrderStack(tree.root);
    }
}
