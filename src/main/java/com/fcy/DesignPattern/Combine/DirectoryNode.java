package com.fcy.DesignPattern.Combine;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  2:20
 */
public class DirectoryNode extends Node {
    public DirectoryNode(String name) {
        super(name, true);
    }

    @Override
    protected Node add(Node node) throws Exception {
        nodes.add(node);
        return this;
    }

    @Override
    public void dis() {
        System.out.println(name);
        super.dis();
    }
}
