package com.fcy.AQS.AQSDemo;

import com.fcy.AQS.MyAQS.ReentrantLockNote;
import com.fcy.Log.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
*@descripiton 公平锁和非公平锁的区别在于 在一个线程已经占用锁的情况下，另一个线程尝试申请锁的时候，
 * 公平锁会直接把这个线程放入队列中
 * 而非公平锁会尝试获取锁，如果获取成功则直接跳过队列中的其他线程而直接获取锁
 * 体现在方法上就是在tryAcquire方法中
 * 公平锁首先会使用hasQueuePrecessor方法判断是否有前驱，有的话就直接获取失败
 * 非公平锁不会判断是否有前驱，而是直接尝试设置state变量
*/
public class ReentrantLockDemo {
    private static ReentrantLockNote lock=new ReentrantLockNote();
    private static Condition condition=lock.newCondition();
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) {
            service.submit(() -> {
                log.infoToScreen("start apply for lock");
                lock.lock();
                try {
                    log.infoToScreen("get lock");
                    //Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    log.infoToScreen("return lock");
                    lock.unlock();
                }
            });
        }
        service.shutdown();
    }
}
