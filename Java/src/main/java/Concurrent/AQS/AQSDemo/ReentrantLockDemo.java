package Concurrent.AQS.AQSDemo;

import Concurrent.AQS.MyAQS.ReentrantLockNote;
import com.fcy.Util.Log.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;

/**
*@descripiton
 * 公平锁和非公平锁的区别在于 在一个线程已经占用锁的情况下，另一个线程尝试申请锁的时候，
 * 公平锁会直接把这个线程放入队列中
 * 而非公平锁会尝试获取锁，如果获取成功则直接跳过队列中的其他线程而直接获取锁
 * 体现在方法上就是在tryAcquire方法中
 * 公平锁首先会使用hasQueuedPredecessors方法判断是否有前驱，有的话就直接获取失败
 * 非公平锁不会判断是否有前驱，而是直接尝试设置state变量
*/
public class ReentrantLockDemo {
    private static ReentrantLockNote lock=new ReentrantLockNote();
    /**
     * Condition 条件锁
     * Condition.await方法会释放以及获取的锁
     * Condition.signal方法不会立刻释放锁,而是等待锁中的代码执行完毕
     * 如果代码是个死循环则一直不会释放锁
     * 和synchronized中的notify和wait类似
    */
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
