package com.fcy.Util.FileStateCheck;

import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-19  2:37
 */
@Data
public class Tree {
    private FileNode root;
    private static int count=0;
    private Queue<FileNode> queue=new LinkedList<>();
    private Stack<FileNode> stack=new Stack<>();
    public Tree(){

    }
    public void startThreadCheck(long time){
        checkModified c=new checkModified(time);
        c.start();
    }
    private class checkModified extends Thread{
        private static final long DEFAULT_INTERVAL=10000;
        public long time;
        public checkModified(long t){
            this.time=t;
        }
        public checkModified(){
            this.time=DEFAULT_INTERVAL;
        }
        @Override
        public void run() {
            while (true){
                System.out.println("----开始检测文件修改----");
                try {
                    Thread.sleep(time);
                    check();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void check(){
            queue.offer(root);
            long start=System.currentTimeMillis();
            bfs(e->{
                FileNode node=(FileNode)e;
                if (node.modified()){
                    System.out.println(new Date()+node.getFile().getPath()+"---已修改!");
                    node.resume();
                }else{
                    count++;
                }
            });
            long end=System.currentTimeMillis();
            System.out.println(count+"所有文件未修改!---cost time:"+(end-start));
            count=0;
        }
    }
    public void insert(File f){
        if (root==null){
            root=new FileNode(f);
            return;
        }
        queue.offer(root);
        FileNode node=root;
        while (true){
            if (node.getLeft()==null){
                node.setLeft(new FileNode(f));
                return;
            }
            if (node.getRight()==null){
                node.setRight(new FileNode(f));
                return;
            }
            if (f.length()>node.getFile().length()){
                node=node.getLeft();
            }else{
                node=node.getRight();
            }
        }
    }
    public void bfs(Action action){
        bfs0(action);
    }
    public int getHeight(){
        return getHeight(root);
    }
    public int getHeight(FileNode root){
        if(root==null){
            return 0;
        }
        Queue<FileNode> queue=new LinkedList<>();
        queue.add(root);
        int height=1;
        while(!queue.isEmpty()){
            FileNode node=queue.peek();
            if(node.getLeft()==null&&node.getRight()==null){
                break;
            }else{
                if(node.getLeft()!=null){
                    queue.add(node.getLeft());
                }
                if(node.getRight()!=null){
                    queue.add(node.getRight());
                }
                queue.poll();
                height++;
            }

            //System.out.println(height);

        }
        return height;
    }
    private void bfs0(Action visit){
        while (queue.peek()!=null){
            FileNode node=queue.poll();
            visit.action(node);
            if (node.getLeft()!=null){
                queue.offer(node.getLeft());
            }
            if (node.getRight()!=null){
                queue.offer(node.getRight());
            }
        }
    }
    public void dfs(Action action){
        stack.push(root);
        dfs0(action);
    }
    private void dfs0(Action action){
        while (stack.peek()!=null){
            FileNode node=stack.pop();
            action.action(node);
            if (node.getRight()!=null){
                stack.push(node.getRight());
            }
            if (node.getLeft()!=null){
                stack.push(node.getLeft());
            }
        }
    }
    public int size(){
        return size(root);
    }
    private int size(FileNode node){
        if (node==null){
            return 0;
        }
        int left=size(node.getLeft());
        int right=size(node.getRight());
        return left+right+1;
    }
    public int height(){
        return height(root);
    }
    private int height(FileNode node){
        if (node==null){
            return 0;
        }
        int left=height(node.getLeft());
        int right=height(node.getRight());
        return Math.max(left,right)+1;
    }
}
