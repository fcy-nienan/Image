package Advanced.Concurrent.Thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-24  12:06
 */
public class CustomedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(10,20,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        executor.submit(new run());
        Thread.sleep(3000);
        executor.submit(new run1());
        executor.shutdown();
    }
    static class run implements Runnable{
        @Override
        public void run() {
            test();
        }
    }
    static class run1 implements Runnable{
        @Override
        public void run() {
            noti();
        }
    }
    static synchronized void test(){
        System.out.println("entry");
        try {
            CustomedBlockingQueue.class.wait();
            System.out.println("wait after");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit");
    }
    static synchronized void noti(){
        System.out.println("to notify");
        CustomedBlockingQueue.class.notify();
        System.out.println("notify finished");
    }
}
