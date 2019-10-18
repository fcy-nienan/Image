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
    private volatile int x;
    private int y;
    private int z;
    private int s;
    private AtomicInteger a=new AtomicInteger();
    private static long zOffset;
    private static Unsafe unsafe;
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
    private void update(Object o,long offset){
        int c=0;
        do{
            c=unsafe.getIntVolatile(o,offset);
        }while (!unsafe.compareAndSwapInt(o,offset,c,c+1));
    }
    public void adds(){
        synchronized (this){
            s++;
        }
    }
    public void adda(){
        a.getAndIncrement();
    }
    public final void getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = unsafe.getIntVolatile(var1, var2);
        } while(!unsafe.compareAndSwapInt(var1, var2, var5, var5 + var4));
    }
    public void addz(){
        getAndAddInt(test,zOffset,1);

    }
    public void addx(){
        x++;
    }
    public void addy(){
        y++;
    }
    class xt implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10000000;i++){
                test.addx();
            }
        }
    }
    class yt implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                test.addy();
            }
        }
    }
    class zt implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                test.addz();
            }
        }
    }
    class st implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                adds();
            }
        }
    }
    class at implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                adda();
            }
        }
    }
    public static void main(String args[]) {
        test.test();
    }
    private void test(){
        List<Future> futures=new ArrayList<>();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(100,200,0, TimeUnit.SECONDS,new ArrayBlockingQueue(100));
        long start=System.currentTimeMillis();
        for (int i=0;i<20;i++){
//            Future<?> submit = executor.submit(test.new xt());
//            Future<?> submit1 = executor.submit(test.new yt());
            Future<?> submit2 = executor.submit(test.new zt());
//            Future<?> submit3 = executor.submit(test.new st());
//            Future<?> submit4 = executor.submit(test.new at());
//            futures.add(submit);
//            futures.add(submit1);
            futures.add(submit2);
//            futures.add(submit3);
//            futures.add(submit4);
        }
        while (true){
            int count=0;
            for (int i=0;i<futures.size();i++){
                if (!futures.get(i).isCancelled()&&futures.get(i).isDone()){
                    count++;
                }else{
                    break;
                }
            }
            if (count==futures.size()){
                break;
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));
        System.out.println("x:"+x);
        System.out.println("y:"+y);
        System.out.println("z:"+z);
        System.out.println("s:"+s);
        System.out.println("a:"+a);
        executor.shutdown();

    }
}
