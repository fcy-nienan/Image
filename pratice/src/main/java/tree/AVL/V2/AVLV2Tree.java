package tree.AVL.V2;

import tree.AVLNode;

public class AVLV2Tree {
    private AVLNode root;
//    获取二叉树的高度,两种方法
    public int getHeight(AVLNode node){
        if (node==null){
            return 1;
        }
        int left=getHeight(node.getLeft());
        int right=getHeight(node.getRight());
        return Math.max(left,right);
    }
    public int getHeight2(AVLNode node){
        if (node==null){
            return 0;
        }
        int left=getHeight2(node.getLeft());
        int right=getHeight2(node.getRight());
        return 1+Math.max(left,right);
    }
//    插入一个AVL节点
    public boolean insert(int value){
        AVLNode current=root;
        while (true){
            if (value>current.getValue()){
                current=current.getLeft();
            }
        }
    }
//    删除一个AVL的节点?
//    下面的实现方式只是保证了二叉搜索树的性质,右子树<根节点<左子树,但是并没有保持平衡性的标志在里面
//    节点中的balanceFactor平衡因子都没用到
    public boolean remove(int value){
        AVLNode current=root,pre=root;
        boolean right=true,result=false;
        while (true){
            if (current==null){
                result=false;
                break;
            }
            if (value>current.getValue()){
                pre=current;
                current=current.getRight();
                right=false;
                result=true;
            }else if (value<current.getValue()){
                pre=current;
                current=current.getLeft();
                right=true;
                result=true;
            }else{
//                如果需要删除的节点没有叶子节点
                if (current.getLeft()==null&&current.getRight()==null){
                    if (right){//如果是其父节点的右节点
                        pre.setRight(null);
                    }else{//如果是其父节点的左节点
                        pre.setLeft(null);
                    }
                    break;
                }
//                如果需要删除的节点有一个节点
                if (current.getLeft()!=null){//如果左节点有值
                    if (right){
                        pre.setRight(current.getLeft());
                        break;
                    }
                }
                if (current.getRight()!=null){//如果右节点有值
                    if (!right){
                        pre.setLeft(current.getRight());
                        break;
                    }
                }
//                如果需要删除的节点有两个节点
                if (current.getLeft()!=null&&current.getRight()!=null){
                    AVLNode nextNode=current.getRight();
                    nextNode.setLeft(current.getLeft());
                    nextNode.setRight(nextNode.getRight());
                    current.setLeft(null);
                    current.setRight(null);
                    current=null;
                    if (right){
                        pre.setRight(nextNode);
                    }else{
                        pre.setLeft(nextNode);
                    }
                    break;
                }
                result=true;
            }
        }
        return result;
    }
}
