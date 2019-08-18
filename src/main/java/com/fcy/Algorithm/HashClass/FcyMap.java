package com.fcy.Algorithm.HashClass;

import java.util.HashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-11  12:12
 */
public class FcyMap<K,V> {
    private Node[] table;
    private static final int Default_Capacity=16;
    private static final float Default_LoadFactor=0.75f;
    private int threshold;
    private float loadFactor;
    private int size;
    public void put(K k,V v){
        if (table==null){
            resize();
        }else{
            table[size]=new Node(k,v);
        }
    }
    public V get(K k){
        V v=null;
        return v;
    }
    public int computeHashIndex(K k){
        return (k.hashCode()>>16)%100;
    }
    public Node[] resize(){
        if (table==null){
            table=new Node[Default_Capacity];
            threshold= (int) (Default_Capacity*Default_LoadFactor);
            return table;
        }
        int oldCapacity=table.length;
        if (table.length==Integer.MAX_VALUE){
            throw new OutOfMemoryError("exceed int type limit!");
        }
        int newCapacity,newThreshold;
        newCapacity=oldCapacity<<1;
        newThreshold= (int) (newCapacity*loadFactor);
        threshold=newThreshold;
        Node[] newTab=new Node[newCapacity];

        for(int i=0;i<table.length;i++){
            newTab[i]=table[i];
            if (table[i].next!=null){
                Node node=table[i].next;
                Node curNode=newTab[i];
                while(node!=null){
                    curNode.next=node;
                    curNode=curNode.next;
                    node=node.next;
                }
            }
        }
        return newTab;
    }
    class Node<K,V>{
        K k;
        V v;
        Node next;
        public Node(K k,V v){
            this.k=k;
            this.v=v;
        }
    }
}
