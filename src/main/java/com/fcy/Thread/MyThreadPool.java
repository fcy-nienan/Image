package com.fcy.Thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//实现自己的线程池
public class MyThreadPool {
    private List<Thread> worker;
    private LinkedBlockingQueue<Runnable> jober;
    private int currentPoint=0;
    private ReentrantLock lock;
    private Condition notFull;
    public MyThreadPool(int size){
        this.worker=new ArrayList<>(size);
        this.jober=new LinkedBlockingQueue<>();
        lock=new ReentrantLock();
        notFull=lock.newCondition();

        for(int i=0;i<size;i++){
            Thread t1=new Thread(()->{
                execute();
            });
            t1.setName("Thread:"+i);
            t1.start();
            this.worker.add(t1);
        }
    }
    public void subTask(Runnable runnable){
        lock.lock();
        try{
            currentPoint++;
            jober.add(runnable);
//            notFull.signal();
        }finally{
            lock.unlock();
        }
    }
    public void execute(){
        while(true){
            Runnable runnable=null;
            System.out.println(Thread.currentThread().getName());
            try {
                runnable = jober.take();
                runnable.run();

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally{
            }
        }
    }

    public static void main(String[] args)throws Exception {
        Thread t1=new Thread(()->{
           for(int i=0;i<10;i++){
               System.out.println(i+"  "+Thread.currentThread().getName());
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread t2=new Thread(()->{
            for(int i=10;i<20;i++){
                System.out.println(i+"  "+Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*MyThreadPool pool=new MyThreadPool(3);
        pool.subTask(t1);
        pool.subTask(t2);*/
//        pool.test();
//        MyThreadPoolSyn syn=new MyThreadPoolSyn(3);
//        syn.submit(t1);
//        syn.submit(t2);

    }
    public void test(){
        ExecutorService service=Executors.newFixedThreadPool(3);
        Thread t1=new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i+"  "+Thread.currentThread().getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=10;i<20;i++){
                System.out.println(i+"  "+Thread.currentThread().getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        service.execute(t1);
        service.execute(t2);
    }
    class MyThreadPoolSyn{
        private List<Thread> worker;
        private LinkedList<Runnable> jober;
        public MyThreadPoolSyn(int size){
            this.worker=new ArrayList<>();
            this.jober=new LinkedList<>();
            for(int i=0;i<size;i++){
                Thread t=new Thread(()->{
                    execute();
                });
                t.setName("pool:"+i);
                t.start();
                this.worker.add(t);
            }
        }
        public void submit(Runnable runnable){
            synchronized(jober){
                jober.add(runnable);
                jober.notify();
            }
        }
        public void execute(){
            while(true){
                Runnable runnable=null;
                synchronized (jober){
                    if(jober.isEmpty()){
                        try {
                            jober.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    runnable=jober.getLast();
                    jober.removeLast();
                }
                runnable.run();
            }
        }
    }

}
