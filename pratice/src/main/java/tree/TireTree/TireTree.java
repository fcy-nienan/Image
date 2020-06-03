package tree.TireTree;

import tree.TireTreeNode;

import java.util.Set;

public class TireTree {
    public boolean exists(String s, TireTreeNode root){
        char[] val=s.toCharArray();
        TireTreeNode current=root;
        int i=0;
        while (true){
            if (exists(val[i],current)){

            }
        }

    }
    public boolean childExists(char c, Set<TireTreeNode> set){
        return set.contains(new TireTreeNode(c));
    }
    public boolean exists(char c, TireTreeNode root){
        return root.getValue()==c&&root.isEnd();
    }
}
