package Concurrent.AQS.AQSDemo;


import lombok.extern.java.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Log
public class SemaphoreDemo {
    private static ExecutorService executor= Executors.newCachedThreadPool();
    private static final int threadCount=100;
    private static int x=0;
    private static final Semaphore semaphore=new Semaphore(1);

    public static void main(String[] args) {
        for(int i=0;i<threadCount;i++){
            executor.submit(new T());
        }
        executor.shutdown();
    }
    static class T implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire(1);
                dis();
                semaphore.release(1);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                return;
            }
        }
    }
    public  static void dis() throws InterruptedException {
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(1000);
        log.info("{}"+Thread.currentThread().getId());
        System.out.println("Next:");

    }
}
