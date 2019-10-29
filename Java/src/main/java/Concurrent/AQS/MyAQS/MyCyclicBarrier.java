package Concurrent.AQS.MyAQS;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descripiton: 自己实现一个CyclicBarrier的线程工具
 * 该类并没有继承AQS，而是使用了可冲入锁和条件队列来完成的
 * @author: fcy
 * @date: 2018-07-28  9:59
 */
public class MyCyclicBarrier {
    private ReentrantLock lock;
    private Condition condition;
    private int parties;
    private int count;

    public MyCyclicBarrier(int parties) {
        this.parties = parties;
        this.count=parties;
        this.lock=new ReentrantLock();
        this.condition=this.lock.newCondition();
    }
    public void await() throws InterruptedException {
        this.lock.lock();
        int index=--count;

        if(index==0){
            condition.signalAll();
        }else if(index>0){
            condition.await();
        }
        this.lock.unlock();
    }
    public static void main(String args[]) {

    }
}
