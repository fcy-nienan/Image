package com.fcy.UnSafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
public class UnSafeDemo {
    private volatile int state;//volatile保证state的可见性
    private static long stateOffset;
    private static Unsafe unsafe;
    static{
        try {
            Field field=Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe=(Unsafe)field.get(null);
            stateOffset=unsafe.objectFieldOffset(UnSafeDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public boolean setState(int update) {
        return unsafe.compareAndSwapInt(this,stateOffset,state,update);
    }
    public int getAndIncrement(int add){
        int temp=state;
        do{
            temp=unsafe.getIntVolatile(this,stateOffset);
        }while(!unsafe.compareAndSwapInt(this,stateOffset,temp,temp+add));
        return temp;
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();
        ThreadPoolExecutor executor=(ThreadPoolExecutor)service;
        final UnSafeDemo demo=new UnSafeDemo();
        for(int i=0;i<10;i++){
            service.submit(()->{
               for(int j=0;j<100000;j++){
                   demo.getAndIncrement(1);
               }
            });
            service.submit(()->{
                while(true){
                    demo.setState(10);
                }
            });
        }
        executor.shutdown();
        Thread.sleep(8000);
        System.out.println(executor.getActiveCount());
        System.out.println(demo.state);

    }

}
