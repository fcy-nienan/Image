package com.fcy.Collection;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-27  17:18
 */
public class MyBlockingQueue extends AbstractQueue implements BlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue demo=new MyBlockingQueue();
        for(int i=0;i<16;i++){
            demo.put(i+"fcy");
        }
        System.out.println("Put Finished !");
        for(int i=0;i<16;i++){
            System.out.println(demo.take());
        }
        System.out.println("Take Finished !");
    }
    private ReentrantLock lock=new ReentrantLock();
    private Condition notFull=lock.newCondition();
    private Condition notEmpty=lock.newCondition();
    private Object[] elements;
    private int size;
    private int takeIndex;
    private int putIndex;
    private static final int DEFAULT_CAPACITY=16;
    public MyBlockingQueue(int capacity){
        this.elements=new Object[capacity];
    }
    public MyBlockingQueue(){
        this.elements=new Object[DEFAULT_CAPACITY];
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        lock.lock();
        try{
            return size;
        }finally {
            lock.unlock();
        }
    }
    public void checkNotNull(Object o){
        if(o==null)
            throw new NullPointerException();
    }
    @Override
    public void put(Object o) throws InterruptedException {
        checkNotNull(o);
        lock.lockInterruptibly();
        try{
            while(size==elements.length)
                notFull.await();
            enque(o);
        }finally {
            lock.unlock();
        }
    }
    public void enque(Object object){
        elements[putIndex]=object;
        if(++putIndex==elements.length)
            putIndex=0;
        size++;
        notEmpty.signal();
    }
    public Object deque(){
        Object object=elements[takeIndex];
        elements[takeIndex]=null;
        if(++takeIndex==elements.length)
            takeIndex=0;
        size--;
        notFull.signal();
        return object;
    }

    @Override
    public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Object take() throws InterruptedException {
        lock.lock();
        try{
            while(size==0)
                notEmpty.await();
            Object o=deque();
            return o;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        lock.lock();
        try{
            return elements.length-size;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int drainTo(Collection c) {
        return 0;
    }

    @Override
    public int drainTo(Collection c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(Object o) {
        lock.lock();
        try{
            if(size==elements.length)
                return false;
            enque(o);
        }finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public Object poll() {
        lock.lock();
        try{
            return deque();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Object peek() {
        lock.lock();
        try{
            return elements[takeIndex];
        }finally {
            lock.unlock();
        }
    }
}
