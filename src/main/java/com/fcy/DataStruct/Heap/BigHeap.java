package com.fcy.DataStruct.Heap;

import lombok.Getter;
import lombok.Setter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-03  21:42
 *                                                                              1
 *                                                                          6       2
 *                                                                       5      8 9   34
 *                                                                      65
 *
 */
public class BigHeap {
    @Setter
    @Getter
    private Integer[] data;
    public static void main(String[] args) {
        BigHeap heap=new BigHeap();
        heap.setData(new Integer[]{1,6,2,5,8,9,34,65});
        heap.createHeap();
        for(int i=0;i<heap.getData().length;i++){
            System.out.println(heap.getData()[i]);
        }
    }
    public void createHeap(){
        for(int i=(data.length)/2-1;i>=0;i--){//8-->3,2,1,0
            heapIfy(i);
        }
    }
    public void heapIfy(int index){//将子节点和根节点比较,根据大小堆得特性决定是否交换位置
        int left=left(index);
        int right=right(index);
        int smallest=index;
        if (left<data.length&&data[left]<data[index])
            smallest=left;
        if (right<data.length&&data[right]<data[index])
            smallest=right;
        if (index==smallest)
            return;
        int tmp=data[index];
        data[index]=data[smallest];
        data[smallest]=tmp;
        heapIfy(smallest);
    }
    private int left(int index){
        return (index<<2)+1;
    }
    private int right(int index){
        return (index+1)<<2;
    }

}
