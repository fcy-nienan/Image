package com.fcy.Algorithm.Sort;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-07-09  17:18
 */
public class HeapSort1 {
    private int[] data=new int[10];
    public HeapSort1(){
    }
    public int left(int index){
        return index<<1;
    }
    public int right(int index){
        return (index<<1)+1;
    }
    public int parent(int index){
        if (index==0)throw new IllegalArgumentException("index zero no parent node!");
        return index/2;
    }
    public int leftValue(int index){
        return data[left(index)];
    }
    public int rightValue(int index){
        return data[right(index)];
    }
    public int parentValue(int index){
        return data[parent(index)];
    }
    public static void main(String[] args) {
        PriorityQueue queue;
    }


}
