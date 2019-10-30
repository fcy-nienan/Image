package DesignPattern.Combine;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  2:07
 */
@ToString
public abstract class Node {
    protected String name;
    protected boolean flag;
    protected List<Node> nodes;
    private int level=0;
    public Node(String name,boolean flag){
        this.name=name;
        this.flag=flag;
        if (flag)
            nodes=new ArrayList<>();
    }
    protected Node add(Node node) throws Exception {
        throw new Exception("Invalid Operation!");
    }
    public void dis(){
        if (nodes!=null){
            nodes.forEach(e->{
                e.dis();
            });
        }else{
            System.out.println(this.name);
        }
    }
}
