package CustomerLockDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThread extends Thread{
    private AtomicInteger integer;
    private CountDownLatch latch;
    private int count;
    public AtomicThread(AtomicInteger i,CountDownLatch latch,int count){
        this.integer=i;
        this.latch=latch;
        this.count=count;
    }
    @Override
    public void run() {
        for (int i=0;i<count;i++) {
            integer.getAndIncrement();
        }
        latch.countDown();
    }
}
