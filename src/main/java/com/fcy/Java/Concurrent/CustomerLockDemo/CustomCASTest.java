package com.fcy.Java.Concurrent.CustomerLockDemo;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
//MESI协议  若缓存行处于S状态,如果此时有另一个处理器发出了Invalid信息
//而当前处理器又已经读取了该缓存行的数据进行+1操作,那么如果当前处理器写入缓存中时Invalid信息还没来当前值就有效
//如果Invalid先一步到来,那么当前CPU写入缓存行的时候发现缓存行已经失效了,那么会重新从内存中读取数据
//暂时是这么理解的
public class CustomCASTest{
    private static final int threadCount=24;
    private static final int count=10000000;
    public static void main(String args[]) throws InterruptedException {
        startAtomicTask();
        startAtomicTask();
        startAtomicTask();
        startFcyTask();
        startFcyTask();
        startFcyTask();
        startSynchronizedTask();
    }
    static class SynchronizedObject{
        private long x;
        public void add(){
            x++;
        }
        public long get(){
            return x;
        }
    }
    private static void startSynchronizedTask()throws InterruptedException{
        long start,end;
        SynchronizedObject object=new SynchronizedObject();
        CountDownLatch integerLatch=new CountDownLatch(threadCount);
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            SynchronizedThread thread=new SynchronizedThread(object,integerLatch,count);
            thread.start();
        }
        integerLatch.await();
        end=System.currentTimeMillis();
        System.out.println(object.get()+"   synchronized cost time:"+(end-start));
    }
    private static void startAtomicTask() throws InterruptedException {
        long start,end;
        AtomicInteger atomicInteger=new AtomicInteger();
        CountDownLatch integerLatch=new CountDownLatch(threadCount);
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            AtomicThread thread=new AtomicThread(atomicInteger,integerLatch,count);
            thread.start();
        }
        integerLatch.await();
        end=System.currentTimeMillis();
        System.out.println(atomicInteger.get()+"   AtomicInteger cost time:"+(end-start));
    }
    private static void startFcyTask()throws InterruptedException{
        long start,end;
        FcyAtomicInteger fcyAtomicInteger=new FcyAtomicInteger();
        CountDownLatch fcyLatch=new CountDownLatch(threadCount);
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            FcyAtomicThread thread=new FcyAtomicThread(fcyAtomicInteger,fcyLatch,count);
            thread.start();
        }
        fcyLatch.await();
        end=System.currentTimeMillis();
        System.out.println(fcyAtomicInteger.get()+"   FcyAtomicInteger cost time:"+(end-start));
    }

}

