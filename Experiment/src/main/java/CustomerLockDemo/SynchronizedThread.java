package CustomerLockDemo;

import java.util.concurrent.CountDownLatch;

public class SynchronizedThread extends Thread {
    private CountDownLatch latch;
    private int count;
    private CustomCASTest.SynchronizedObject integer;
    public SynchronizedThread(CustomCASTest.SynchronizedObject integer, CountDownLatch latch, int count){
        this.integer=integer;
        this.latch=latch;
        this.count=count;
    }
    @Override
    public void run() {
        synchronized (SynchronizedThread.class) {
            for (int i = 0; i < count; i++) {
                integer.add();
            }
            latch.countDown();
        }
    }
}
