package com.fcy.Java.Concurrent.CustomerLockDemo;

import java.util.concurrent.CountDownLatch;

public class MESIThread extends Thread{
    private MESITest object;
    private CountDownLatch latch;
    private int count;
    private long cost;
    public long getCost(){
        return this.cost;
    }
    public MESIThread(MESITest t,CountDownLatch latch,int count){
        this.object=t;
        this.latch=latch;
        this.count=count;
    }
    @Override
    public void run() {
        long start,end;
        start=System.currentTimeMillis();
        for (int i=0;i<count;i++) {
            object.getAndIncrement();
        }
        end=System.currentTimeMillis();
        cost=end-start;
        latch.countDown();
    }
}
