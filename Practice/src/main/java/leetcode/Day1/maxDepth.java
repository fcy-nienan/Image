package leetcode.Day1;
/*
 * Author:fcy
 * Date:2020/2/6 23:58
*/

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class maxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    /*
    * BFS层次遍历,重点是通过levelSize来实现
    * */
    public int maxDepthStack(TreeNode root){
        if (root==null)return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int depth=0;
        while (!queue.isEmpty()){
            depth++;
            int levelSize=queue.size();
            for (int i=0;i<levelSize;i++) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return depth;
    }
}
