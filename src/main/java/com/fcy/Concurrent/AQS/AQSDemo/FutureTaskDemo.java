package com.fcy.Concurrent.AQS.AQSDemo;

import java.util.concurrent.*;

public class FutureTaskDemo {
    static long x=0;
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue queue=new SynchronousQueue();
        System.out.println(queue.offer("kk"));
        System.out.println(queue.offer("kk"));
        ConcurrentLinkedQueue linkedQueue;

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ExecutorService service=Executors.newCachedThreadPool();
        Future task=service.submit(runnable);
        FutureTask task1;
        Thread.sleep(3000);
        System.out.println(task.isDone());
        System.out.println(task.isCancelled());
        task.cancel(false);
        System.out.println(task.isCancelled());
        /*
         * Future获取线程的执行结果，调用cancel方法不会使线程暂停，
         * 只会将线程的中断标志位设置为true，如果线程调用了sleep，wait或join
         * 并且置该方法的参数为true，
         * 则会抛出InterruptException异常
         * 否则什么也不做
         *
         *
         * */

    }
}
