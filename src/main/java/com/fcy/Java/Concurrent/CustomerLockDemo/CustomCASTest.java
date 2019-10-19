package com.fcy.Java.Concurrent.CustomerLockDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
//MESI协议  若缓存行处于S状态,如果此时有另一个处理器发出了Invalid信息
//而当前处理器又已经读取了该缓存行的数据进行+1操作,那么如果当前处理器写入缓存中时Invalid信息还没来当前值就有效
//如果Invalid先一步到来,那么当前CPU写入缓存行的时候发现缓存行已经失效了,那么会重新从内存中读取数据
//暂时是这么理解的
public class CustomCASTest{
    private static final int count=10000000;
    public static void main(String args[]) throws InterruptedException {
        int threadCount=12;
        long start,end;
        AtomicInteger atomicInteger=new AtomicInteger();
        FcyAtomicInteger fcyAtomicInteger=new FcyAtomicInteger();
        CountDownLatch integerLatch=new CountDownLatch(threadCount);
        CountDownLatch fcyLatch=new CountDownLatch(threadCount);

        AtomicThread[] threads1=new AtomicThread[threadCount];
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            AtomicThread thread=new AtomicThread(atomicInteger,integerLatch,count);
            thread.start();
            threads1[i]=thread;
        }
        integerLatch.await();
        end=System.currentTimeMillis();
        long atomicTotal=0;
        for (AtomicThread atomicThread : threads1) {
            atomicTotal+=atomicThread.getCost();
        }
        System.out.println(atomicInteger.get()+"   cost time:"+(end-start));
        System.out.println(atomicInteger.get()+"   total cost time:"+atomicTotal);







        FcyAtomicThread[] threads=new FcyAtomicThread[threadCount];
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            FcyAtomicThread thread=new FcyAtomicThread(fcyAtomicInteger,fcyLatch,count);
            thread.start();
            threads[i]=thread;
        }
        fcyLatch.await();
        end=System.currentTimeMillis();
        long customerTotal=0;
        for (FcyAtomicThread atomicThread : threads) {
            customerTotal+=atomicThread.getCost();
        }
        System.out.println(fcyAtomicInteger.get()+"   cost time:"+(end-start));
        System.out.println(fcyAtomicInteger.get()+"   total cost time:"+customerTotal);


    }
}

