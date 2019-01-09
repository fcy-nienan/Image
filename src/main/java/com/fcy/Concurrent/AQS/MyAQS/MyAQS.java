package com.fcy.Concurrent.AQS.MyAQS;

import com.fcy.Util.Log.log;

import java.util.concurrent.*;

/*
* 通过实验我们发现
* aqs队列中的头结点的waitStatus的状态初始化的值为0,然后由该节点的next节点设置为-1
* 唤醒的时候一般是唤醒头结点的next节点，如果next节点为null或者被取消了，
* 则从尾到头寻找第一个节点为Node.SIGNAL状态的节点
*
* 头结点的thread为null,该操作在setHead方法中进行,当在等待的时候获取成功后会调用setHead方法
* 把当前线程的节点设置为头结点，并且将该节点的thread执行null
*
*
*
* AQS的tryAcquire尝试获取锁，该方法是通过改变state的值来实现的
* state的值为1或者0  互斥锁
* state的值为多个    共享锁
*       state为5 每次有线程来的时候就调用tryAcquire方法尝试获取锁，获取成功就自己操作state变量
*       并按自己的逻辑返回true或者false
*
*       刚开始
*       head    tail
*       node1
*       node1   node2
*       node2
* */
/*
* tryAcquire
* tryReleased
*
*
*
*
*
* */
public class MyAQS{
    private static int x=0;
    private static Object obj=new Object();
    public static void main(String[] args) throws Exception {
//        testReentrantLock();
//        testCountDownLatch();
//        testReentrantReadWriteLock();
//        testCyclicBarrier();
        testCondition();
    }
    public static void testCondition()throws Exception{
        ArrayBlockingQueue blockingQueue;
        ConditionBlockingQueue queue=new ConditionBlockingQueue();
        ExecutorService service=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) {
            service.submit(() -> {
                while (true) {
                    try {
                        long id = Thread.currentThread().getId();
//                        Thread.sleep(100);
                        System.out.println("Thread Id:  "+id+"  Start Take");
                        Object object = queue.take();
                        System.out.println("Thread Id:  " + id + "    Take " + object);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(1000);
        queue.myPut("fcy");
//        int i=0;
//        while(true){
//            i++;
//            System.out.println("Put "+i);
//            queue.put(i+"fcy");
//        }


    }
    public static void testReentrantLock() throws Exception {
        ReentrantLockNote note=new ReentrantLockNote();
//        ReentrantLock note=new ReentrantLock();
        ExecutorService executorService=Executors.newCachedThreadPool();
        for(int i=0;i<3;i++){
            executorService.submit(()->{
                note.lock();
                try {
                    Thread.sleep(100);
                    for(int j=0;j<3000;j++){
                        x++;
                    }
                    System.out.println(x);
                    System.out.println(Thread.currentThread().getId()+"Finished!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    note.unlock();
                }
            });
        }
        Thread.sleep(3000);
        System.out.println(x);
        executorService.shutdown();
    }
    public static void testCountDownLatch()throws Exception{
        CountDownLatchNote note=new CountDownLatchNote(5);
        ExecutorService service=Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++) {
            service.submit(() -> {
                for(int j=0;j<100;j++){
                    synchronized (obj){
                        x++;
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                note.countDown();
                System.out.println("kk");
            });
        }
        note.await();
        service.shutdown();
        System.out.println(x);
    }
    public static void testReentrantReadWriteLock()throws Exception{
        ReentrantReadWriteLockNote note=new ReentrantReadWriteLockNote();
        ExecutorService service=Executors.newCachedThreadPool();
        service.submit(()->{
            note.writeLock().lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                note.writeLock().unlock();
            }
        });
        for(int i=0;i<5;i++){
            service.submit(()->{
                note.readLock().lock();
                try {
                    log.infoToFile("start block");
//                    Thread.sleep(2000);
                    log.infoToFile("block end");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    note.readLock().unlock();
                }
            });
        }
        service.shutdown();
    }
    public static void testCyclicBarrier() throws Exception {
        MyCyclicBarrier barrier=new MyCyclicBarrier(122);
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<122;i++){
            service.submit(()->{
                log.infoToScreen("before block");
                try {
                    Thread.sleep(2000);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.infoToScreen("let us start work");
            });
        }
        service.shutdown();
    }
}
