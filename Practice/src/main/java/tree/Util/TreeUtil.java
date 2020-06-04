package tree.Util;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtil {
    public static void main(String[] args) {

    }
    public static void preOrder(Node root){
        if (root!=null){
            System.out.print(root.value+"\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }
    public static void inOrder(Node root){
        if (root!=null){
            inOrder(root.left);
            System.out.println(root.value);
            inOrder(root.right);
        }
    }
    public static void postOrder(Node root){
        if (root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value);
        }
    }

    public static Node create(int[] array){
        if (array.length==0)return null;
        Queue<Node> linkedList=new LinkedList();
        Node node=new Node(array[0]);
        linkedList.offer(node);
        int i=1;
        while (!linkedList.isEmpty()){
            Node tmp=linkedList.poll();
            if (tmp==null)return node;
            if (i<array.length) {
                if (array[i]==-1){
                    tmp.setLeft(null);
                    i++;
                }else {
                    Node left = new Node(array[i]);
                    tmp.left=left;
                    linkedList.offer(left);
                    i++;
                }
            }
            if (i<array.length) {
                if (array[i]==-1){
                    tmp.setRight(null);
                    i++;
                }else {
                    Node right = new Node(array[i]);
                    tmp.setRight(right);
                    linkedList.offer(right);
                    i++;
                }
            }
        }
        return node;
    }
}
