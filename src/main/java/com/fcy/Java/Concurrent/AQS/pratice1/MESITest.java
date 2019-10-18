package com.fcy.Java.Concurrent.AQS.pratice1;

import com.fcy.Notes.MESI;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

//MESI协议  若缓存行处于S状态,如果此时有另一个处理器发出了Invalid信息
//而当前处理器又已经读取了该缓存行的数据进行+1操作,那么如果当前处理器写入缓存中时Invalid信息还没来当前值就有效
//如果Invalid先一步到来,那么当前CPU写入缓存行的时候发现缓存行已经失效了,那么会重新从内存中读取数据
//暂时是这么理解的
class t{
    private int kkk;
    int ddd;
    protected int ccc;
    public long dddd;
}
public class MESITest extends t{
    private static volatile int x;
    private static int y;
    private static int z;
    private static long zOffset;
    private static Unsafe unsafe;
    static {

    }
    public static void addz(){

    }
    public static void addx(){
        x++;
    }
    public static void addy(){
        y++;
    }
    static class xt implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10000000;i++){
                addx();
            }
        }
    }
    static class yt implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                addy();
            }
        }
    }
    public static void main(String args[]) {
        Field[] declaredFields = MESITest.class.getDeclaredFields();
        System.out.println("decl fields!");
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        System.out.println("fields!");
        for (Field field : MESITest.class.getFields()) {
            System.out.println(field.getName());
        }
    }
    private static void test(){
        List<Future> futures=new ArrayList<>();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(100,200,0, TimeUnit.SECONDS,new ArrayBlockingQueue(100));
        for (int i=0;i<20;i++){
            Future<?> submit = executor.submit(new xt());
            Future<?> submit1 = executor.submit(new yt());
            futures.add(submit);
            futures.add(submit1);
        }
        executor.shutdown();
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
        System.out.println("x:"+x);
        System.out.println("y:"+y);
    }
}
