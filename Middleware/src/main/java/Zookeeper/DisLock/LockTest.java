package Zookeeper.DisLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descripiton:锁？
 * 锁的加锁与释放
 * 未获取锁的对象该如何
 * 谁来通知未获取锁的对象
 * @author: fcy
 * @date: 2018-11-15  0:55
 */
public class LockTest {
    private static ThreadPoolExecutor executor=new ThreadPoolExecutor(20,40,0,TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    private static int x=0;
    private static Lock lock=new ReentrantLock();
    public static void testLock() {
        List<Future> futureList=new ArrayList();
        for(int i=0;i<1000;i++){
            Future future=executor.submit(new task());
            futureList.add(future);
        }
        int j=0;
        while(true){
            Future future=futureList.get(j);
            if (future.isDone()){
                j++;
            }
            if (j==1000)
                break;
        }
        System.out.println(x);
        executor.shutdown();
    }
    public static void main(String[] args) {
        testLock();
    }
    static class task implements Runnable{
        @Override
        public void run() {
//            lock.lock();
            ZooDataSource.lock();
            for(int i=0;i<10000;i++){
                x++;
            }
            ZooDataSource.unlock();
//            lock.unlock();
        }
    }
}
