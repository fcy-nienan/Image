package com.fcy.Java.Concurrent.CustomerLockDemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//MESI协议  若缓存行处于S状态,如果此时有另一个处理器发出了Invalid信息
//而当前处理器又已经读取了该缓存行的数据进行+1操作,那么如果当前处理器写入缓存中时Invalid信息还没来当前值就有效
//如果Invalid先一步到来,那么当前CPU写入缓存行的时候发现缓存行已经失效了,那么会重新从内存中读取数据
//暂时是这么理解的
public class MESITest{
    private volatile int z;
    private static long zOffset;
    private static Unsafe unsafe;
    private static final int count=10000000;
    static {
        try {
            Field field= Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe= (Unsafe) field.get(null);
            zOffset=unsafe.objectFieldOffset(
                    MESITest.class.getDeclaredField("z")
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public int getAndIncrement(){
//        return unsafe.getAndAddInt(this,zOffset,1);
        return this.getAndAddInt(this,zOffset,1);
    }
    public int getAndAddInt(Object object,long offset,int add){
        int c;
        do {
            c=unsafe.getIntVolatile(object,offset);
        }while (!unsafe.compareAndSwapInt(object,offset,c,c+add));
        return c;
    }
    public static void main(String args[]) throws InterruptedException {
        int threadCount=2;
        long start,end;
        AtomicInteger integer=new AtomicInteger();
        CountDownLatch integerLatch=new CountDownLatch(threadCount);
        MESITest mesiTest=new MESITest();
        CountDownLatch mesiLatch=new CountDownLatch(threadCount);


        MESIThread[] threads=new MESIThread[threadCount];
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            MESIThread thread=new MESIThread(mesiTest,mesiLatch,count);
            thread.start();
            threads[i]=thread;
        }
        mesiLatch.await();
        end=System.currentTimeMillis();
        long total=0;
        for (MESIThread thread : threads) {
            total+=thread.getCost();
        }
        System.out.println(mesiTest.z+"   cost time:"+(end-start));
        System.out.println(mesiTest.z+"   total cost time:"+total);

        AtomicThread[] threads1=new AtomicThread[threadCount];
        start=System.currentTimeMillis();
        for (int i=0;i<threadCount;i++){
            AtomicThread thread=new AtomicThread(integer,integerLatch,count);
            thread.start();
            threads1[i]=thread;
        }
        integerLatch.await();
        end=System.currentTimeMillis();
        long atomicTotal=0;
        for (AtomicThread atomicThread : threads1) {
            atomicTotal+=atomicThread.getCost();
        }
        System.out.println(integer.get()+"   cost time:"+(end-start));
        System.out.println(integer.get()+"   total cost time:"+atomicTotal);


    }
}
