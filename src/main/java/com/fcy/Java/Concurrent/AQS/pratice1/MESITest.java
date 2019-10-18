package com.fcy.Java.Concurrent.AQS.pratice1;

import com.fcy.Java.UnSafe.UnSafeDemo;
import com.fcy.Notes.MESI;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

//MESI协议  若缓存行处于S状态,如果此时有另一个处理器发出了Invalid信息
//而当前处理器又已经读取了该缓存行的数据进行+1操作,那么如果当前处理器写入缓存中时Invalid信息还没来当前值就有效
//如果Invalid先一步到来,那么当前CPU写入缓存行的时候发现缓存行已经失效了,那么会重新从内存中读取数据
//暂时是这么理解的
public class MESITest{
    private volatile int z;
    private AtomicInteger a=new AtomicInteger();
    private static long zOffset;
    private static Unsafe unsafe;
    private static final int count=1000000;
    private static MESITest test=new MESITest();
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
        return this.getAndAddInt(this,zOffset,1);
    }
    public int getAndAddInt(Object object,long offset,int add){
        int c;
        do {
            c=unsafe.getIntVolatile(object,offset);
        }while (!unsafe.compareAndSwapInt(object,offset,c,c+add));
        return c;
    }
    public static void main(String args[]) {
        AtomicInteger integer=new AtomicInteger();
        long start=System.currentTimeMillis();
        for (int i=0;i<count;i++) {
            integer.getAndIncrement();
        }
        long end=System.currentTimeMillis();
        System.out.println("atomic:"+(end-start));


        MESITest test=new MESITest();
        start=System.currentTimeMillis();
        for (int i=0;i<count;i++) {
            test.getAndIncrement();
        }
        end=System.currentTimeMillis();
        System.out.println("customer:"+(end-start));

    }
}
