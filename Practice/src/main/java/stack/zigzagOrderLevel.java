package stack;

import tree.Node;
import tree.Util.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class zigzagOrderLevel {
    public static void main(String[] args) {
        Node root= TreeUtil.create(new int[]{3,9,20,-1,-1,7});
        dfs(root);
        List<List<Integer>> list=zigzagLevelOrder(root);
        for(List<Integer> t:list){
            for(int k:t){
                System.out.println(k);
            }
        }
    }
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> lists=new ArrayList<>();
        if (root==null)return lists;
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        boolean face=false;
        while (!stack.isEmpty()){
            Node node;
            List<Integer> listSingle=new ArrayList<>();
            List<Node> tmpList=new ArrayList<>();
            while (!stack.empty()&&(node=stack.pop())!=null){
                listSingle.add(node.value);
                if (face) {
                    if (node.right != null) {
                        tmpList.add(node.right);
                    }
                    if (node.left != null) {
                        tmpList.add(node.left);
                    }
                }else{
                    if (node.left!=null){
                        tmpList.add(node.left);
                    }
                    if (node.right!=null){
                        tmpList.add(node.right);
                    }
                }
            }
            face=!face;
            lists.add(listSingle);
            for(Node n:tmpList){
                stack.push(n);
            }
        }
        return lists;
    }
    public static void dfs(Node root){
        System.out.println(root);
        if (root.left!=null){
            dfs(root.left);
        }
        if (root.right!=null){
            dfs(root.right);
        }
    }
    public static void bfs(Node root){
        if (root!=null){
            System.out.println(root.value);
        }
    }

}
