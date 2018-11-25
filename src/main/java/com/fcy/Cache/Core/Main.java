package com.fcy.Cache.Core;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-23  0:09
 */
public class Main extends AbstractQueue {
    private PriorityQueue<Object> queue=new PriorityQueue<>();
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private Thread leader=null;

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(Object o) {
        return queue.offer(o);
    }

    @Override
    public Object poll() {
        return queue.poll();
    }

    @Override
    public Object peek() {
        return queue.peek();
    }
}
