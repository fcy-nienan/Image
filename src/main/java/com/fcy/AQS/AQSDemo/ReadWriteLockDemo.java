package com.fcy.AQS.AQSDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @descripiton: 测试读写锁
 * @author: fcy
 * @date: 2018-07-26  10:36
 */
public class ReadWriteLockDemo {
    private static ReadWriteLock lock=new ReentrantReadWriteLock();
    public static void main(String args[]) {
//        testReadLock();
        testWriteLock();
    }
    /**
    *@descripiton 测试读锁
    */
    public static void testReadLock(){
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            service.submit(()->{
                lock.readLock().lock();
                System.out.println("start read!");
                try {
                    Thread.sleep(5000);
                    System.out.println("read end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.readLock().unlock();
                }
            });
        }
        /**
        *@descripiton 在读锁被占用的情况下申请写锁
        */
        service.submit(()->{
            System.out.println("start apply for write lock ");
            lock.writeLock().lock();
            System.out.println("get write lock");
            lock.writeLock().unlock();
            System.out.println("return write lock");
        });
        service.shutdown();
    }
    /**
    *@descripiton 测试写锁
    */
    public static void testWriteLock(){
        ExecutorService service=Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            service.submit(()->{
                long id=Thread.currentThread().getId();
                System.out.println(id+":start apply for write lock");
                lock.writeLock().lock();
                System.out.println(id+":get write lock");
                try {
                    Thread.sleep(3000);
                    System.out.println(id+":write end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.writeLock().unlock();
                }
                System.out.println(id+":return write lock");
            });
        }
        /**
        *@descripiton 在写锁被占用的情况下测试读锁
        */
        service.submit(()->{
            long id=Thread.currentThread().getId();
            System.out.println(id+":start apply for read lock");
            lock.readLock().lock();
            System.out.println(id+":get read lock");
            try {
                Thread.sleep(2000);
                System.out.println(id+":read end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.readLock().unlock();
            }
            System.out.println(id+":return read lock");
        });
        service.shutdown();
    }
    
}
