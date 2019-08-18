package com.fcy.Java.Concurrent.AQS.AQSDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
* CyclicBarrier  使一组线程互相等待，可以使得某一组线程同一时刻一起执行
* CyclicBarrier的参数规定需要等待的线程数量
* 每个线程调用了CyclicBarrier的awit方法后该数量减一
* 当所有线程都到齐了后，再一起执行
*
* 一个循环的障碍点
* 每当凑齐了一组线程后就一起执行，否则等待其他线程到来
*
* CountDownLatch是等待一组线程
*
* */
public class CyclicBarrierDemo {
    private static final ExecutorService executor= Executors.newCachedThreadPool();
    private static final CyclicBarrier barrier=new CyclicBarrier(10);
    public static void main(String[] args) throws InterruptedException {
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.isBroken());
        for(int i=0;i<10;i++){
            executor.submit(()->{
                    dis();
            });
        }
        Thread.sleep(5000);
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.isBroken());
        executor.shutdown();
    }
    public static void dis(){
        try {
            barrier.reset();

            System.out.println(Thread.currentThread().getId()+" is ready!");
            barrier.await();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId()+" continue work!");
    }
}
