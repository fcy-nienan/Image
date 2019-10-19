package com.fcy.Java.Concurrent.CustomerLockDemo;

import java.util.concurrent.CountDownLatch;

public class MESIThread extends Thread{
    private MESITest object;
    private CountDownLatch latch;
    private int count;
    public MESIThread(MESITest t,CountDownLatch latch,int count){
        this.object=t;
        this.latch=latch;
        this.count=count;
    }
    @Override
    public void run() {
        for (int i=0;i<count;i++) {
            object.getAndIncrement();
        }
//        latch.countDown();
    }
}
