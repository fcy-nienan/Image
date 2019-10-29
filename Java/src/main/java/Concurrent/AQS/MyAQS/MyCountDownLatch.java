package Concurrent.AQS.MyAQS;

import Log.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-07-28  10:10
 */
public class MyCountDownLatch {
//    private ReentrantLock lock;
    private volatile int count;
//    private Condition condition;
    private Object obj=new Object();

    public MyCountDownLatch(int x){
        this.count=x;
//        lock=new ReentrantLock();
//        condition=lock.newCondition();
    }
    public  void await() throws InterruptedException {
//        lock.lock();
//        try {
        synchronized (obj) {
            System.out.println("start block");
            obj.wait();
            System.out.println("???");
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
    }
    public void countDown(){
//        lock.lock();
        synchronized (obj) {
            this.count--;
            if (count == 0) {
                System.out.println("notify other thread");
                obj.notifyAll();
                while(true){

                }
//            condition.signalAll();
            }

        }
//        lock.unlock();
    }
    public static void main(String args[]) throws InterruptedException {
        MyCountDownLatch latch=new MyCountDownLatch(5);
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            service.submit(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.infoToScreen("end work");
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("all thread run finished!");
        service.shutdown();
    }
}
