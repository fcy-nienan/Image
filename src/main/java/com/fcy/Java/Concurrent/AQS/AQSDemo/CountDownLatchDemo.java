package com.fcy.Java.Concurrent.AQS.AQSDemo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
/*
* CountDownLatch  某个线程等待一组线程运行结束
* 等待一组线程运行一段时间，如果在这段时间内还没有运行完毕
* 则终止等待
*
* CountDownLatch.countDown()只是将计数器减一,他并不会阻塞当前线程
* 相当于一个人告诉多个人去做一件事,然后等待结果,做事的人做完了之后就调用countDown方法,这个方法相当于只
* 做一个标记,然后又去干自己的事了
* 也就是说这个方法并不会阻塞当前线程
*
* CountDownLatch内部使用AQS，状态值为传入的参数
* 每次countDown的时候减一
* 而调用await方法的线程会尝试获取锁
* tryAcquire方法里是直接查看state的变量是否为0
* 是0的话就获取成功
* 不是0的话就放入clh队列中
*
*
* CountDownLatch会阻塞主线程,不会阻塞子线程
* CyclicBarrier不会阻塞主线程，只会阻塞子线程
* */
public class CountDownLatchDemo {
    private static ExecutorService service= Executors.newCachedThreadPool();
    private static int x=10;
    private static final int threadCount=10;
    private static final CountDownLatch latch=new CountDownLatch(threadCount);
    private static final ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        AsCyclicBarrier();
    }
    public static void baseTest() throws InterruptedException {
        for(int i=0;i<10;i++){
            service.submit(new T());
        }
        service.submit(()->{
            System.out.println("before block");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("await:"+x);
        });
        latch.await();
//        latch.await(10, TimeUnit.SECONDS);
        service.shutdown();
        System.out.println("main:"+x);
    }
    static class T implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<100;i++) {
                add();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("released");
        }
    }
    public static void add(){
        lock.lock();
        try {
            x++;
        }finally {
            lock.unlock();
        }
    }
    public static void AsCyclicBarrier(){
        int threads=10;
        CountDownLatch latch=new CountDownLatch(threads);
        ThreadPoolExecutor executor=new ThreadPoolExecutor(20,30,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        for(int i=0;i<10;i++){
            executor.submit(new RunnableTask(latch));
        }
//        executor.shutdown();//等待正在运行的线程结束
        List<Runnable> futureList=executor.shutdownNow();//通过设置线程的中断标志位
//        然后返回运行中的线程引用
    }
    //使用CountDownLatch来实现CyclicBarrier的效果,多个线程到达一个点后等待其他线程一起执行
    static class RunnableTask implements Runnable{
        private CountDownLatch latch;
        public RunnableTask(CountDownLatch latch){
            this.latch=latch;
        }
        @Override
        public void run() {
            long id= Thread.currentThread().getId();
            System.out.println(id+":Start");
            try {
                Thread.sleep(new Random().nextInt(5)*1000);
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                System.out.println(id+"线程被中断!");
            }
            System.out.println(id+":End!");
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
