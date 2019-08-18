package com.fcy.Java.DesignPattern.Combine;

import java.io.File;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  2:17
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Node node=new DirectoryNode("G:\\Code");
        createTree(node);
        node.dis();
    }
    public static void createTree(Node node) throws Exception {
        File file=new File(node.name);
        File[] files=file.listFiles();
        for (File file1:files){
            if (file1.isDirectory()){
                Node node1=new DirectoryNode(file1.getAbsolutePath());
                createTree(node1);                node.add(node1);

            }else{
                node.add(new FileNode(file1.getAbsolutePath()));
            }
        }
    }
}
