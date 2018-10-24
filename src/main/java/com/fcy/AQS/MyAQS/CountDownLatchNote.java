package com.fcy.AQS.MyAQS;
import com.fcy.Log.log;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
public class CountDownLatchNote {
    private static final class Sync extends AQSNote {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        /*
        * 尝试获取
        * 负数表明获取失败
        * 整数表明获取成功
        * */
        protected int tryAcquireShared(int acquires) {
            int c=getState();
            log.infoToFile("尝试获取锁   state状态:"+c);
            if(c==0){
                return 1;
            }else{
                return -1;
            }
        }
        /*
        * 尝试释放共享锁
        * 如果state>0,返回false
        * 如果state==0,返回true
        * */
        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                log.infoToFile("尝试释放共享锁 state状态:"+c);
                if (c == 0)
                    return false;
                int nextc = c-1;
                if (compareAndSetState(c, nextc)) {
                    boolean flag=(nextc == 0);
                    if(flag){
                        log.infoToFile("尝试释放失败  state状态:"+getState());
                    }else{
                        log.infoToFile("尝试释放成功  state状态:"+getState());
                    }
                    return flag;
                }
            }
        }
    }
    private final CountDownLatchNote.Sync sync;
    public CountDownLatchNote(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new CountDownLatchNote.Sync(count);
    }
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
    public boolean await(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }
    public void countDown() {
        sync.releaseShared(1);
    }
    public long getCount() {
        return sync.getCount();
    }
    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}

