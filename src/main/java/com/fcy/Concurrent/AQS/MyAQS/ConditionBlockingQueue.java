package com.fcy.Concurrent.AQS.MyAQS;

import com.fcy.Util.Log.log;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-27  22:34
 */
public class ConditionBlockingQueue extends AbstractQueue implements BlockingQueue {
    private ReentrantLockNote lock=new ReentrantLockNote(true);
    private Condition notFull=lock.newCondition();
    private Condition notEmpty=lock.newCondition();
    private static final int DEFAULT_CAPACITY=16;
    private Object[] elementsDate;
    private int count;
    private int takeIndex;
    private int putIndex;
    public ConditionBlockingQueue(){
        this.elementsDate=new Object[DEFAULT_CAPACITY];
    }
    public ConditionBlockingQueue(int capacity){
        this.elementsDate=new Object[capacity];
    }
    @Override
    public Iterator iterator() {
        return null;
    }
    public void checkNotNull(Object o){
        if(o==null)
            throw new NullPointerException();
    }
    @Override
    public int size() {
        lock.lock();
        try{
            return this.count;
        }finally {
            lock.unlock();
        }
    }
    public void enque(Object object){
        elementsDate[putIndex]=object;
        if(++putIndex==elementsDate.length)
            putIndex=0;
        count++;
        notEmpty.signal();
    }
    public Object deque(){
        Object o=elementsDate[takeIndex];
        elementsDate[takeIndex]=null;
        if(++takeIndex==elementsDate.length)
            takeIndex=0;
        count--;
        notFull.signal();
        return o;
    }
    @Override
    public void put(Object o) throws InterruptedException {
        checkNotNull(o);
        lock.lock();
        try{
            while(count==elementsDate.length)
                notFull.await();
            enque(o);
        }finally {
            lock.unlock();
        }
    }
    @Override
    public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
    public void signal(){
        notEmpty.signal();
    }
    public void signalAll(){
        notEmpty.signalAll();
    }
    public void myPut(Object object){
        lock.lock();
        for(int i=0;i<5;i++) {
            elementsDate[putIndex] = object;
            if (++putIndex == elementsDate.length)
                putIndex = 0;
            count++;
        }
        log.infoToFile("Put Data!");
        notEmpty.signalAll();
        lock.unlock();
    }
    @Override
    public Object take() throws InterruptedException {
        lock.lock();
        try{
            log.infoToFile("Start Take");
            while(count==0)
                notEmpty.await();
            Object object= deque();
            log.infoToFile("Take Successful!");
            return object;
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
        return 0;
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
        return false;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
