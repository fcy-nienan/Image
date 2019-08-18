package com.fcy.Java.Concurrent.Thread;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

/*
* 测试ThreadLocal和直接new一个对象花费时间
* */
public class ThreadLocalVariableDemo {
    static ThreadLocal<SimpleDateFormat> local=ThreadLocal.withInitial(ThreadLocalVariableDemo::init);
    static ThreadLocal<String> localId=ThreadLocal.withInitial(ThreadLocalVariableDemo::create);
    public static String create(){return Thread.currentThread().getName();}
    static ThreadLocal<Integer> localInt=new ThreadLocal<>();
    public static long totalTime=0;
    public static synchronized void totalTimeAdd(long add){
        totalTime+=add;
    }
    public Object ttt(Supplier supplier){
        return supplier.get();
    }
    public static String tttttt(){
        return "lkj";
    }
    public static SimpleDateFormat init(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }
    public static void main(String[] args) {
        System.out.println(localInt.get());
        System.out.println(localInt.get());

        System.out.println(localId.get());
        System.out.println(localId.get());

        System.out.println(local.get());
        System.out.println(local.get());
    }
    public static void testThreadLocal(){
        SimpleDateFormat format=local.get();
    }
    public static void testNew(){
        SimpleDateFormat format=new SimpleDateFormat();
    }
    public static void test(){
        ExecutorService service= Executors.newCachedThreadPool();
        List<Future> futures=new ArrayList<>();
        for(int i=0;i<100;i++){
            Future task=service.submit(()->{
                long start=System.nanoTime();
                for(int j=0;j<100;j++){
                    testThreadLocal();
//                    testNew();
                    /*
                     * testNew    testThreadLocal
                     * 657193878 636140218
                     *2710625838 605416715
                     *3832888574 353331560
                     *4963362276 276755867
                     *19855657694    2117286712
                     *4287468378 797672841
                     *
                     *
                     * */
                }
                long end=System.nanoTime();
                long consume=end-start;
                System.out.println("花费时间:"+consume);
                totalTimeAdd(consume);
            });
            futures.add(task);
        }
        int count=0;
        while(true) {
            ListIterator<Future> iterator=futures.listIterator();
            while (iterator.hasNext()) {
                Future future = iterator.next();
                if (future.isDone()) {
                    count++;
                    iterator.remove();
                }
            }
            if(count==100)
                break;
        }
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(()->{
                while(true){
                    SimpleDateFormat format=local.get();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
        service.shutdown();
        FutureTask task;
        System.out.println("100个线程总共花费时间:"+totalTime);

    }
}
