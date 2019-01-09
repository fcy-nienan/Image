package com.fcy.Java.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-15  17:11
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        testArray();
    }
    /**
    *@descripiton 测试drainTo方法,该方法将阻塞队列的所有元素拉到一个集合中,拉完之后原队列就没数据了
     *一次性获取多个元素,只需要加一次锁就够了
    */
    public static void testArray(){
        BlockingQueue queue=new ArrayBlockingQueue(10);
        for(int i=0;i<10;i++){
            queue.add(i);
        }
        List<Integer> list=new ArrayList();
        int size=queue.drainTo(list);
        System.out.println("Size:"+size);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println(queue.size());

    }

}
