package Concurrent.AQS.MyAQS;
import com.fcy.Util.Log.log;

import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
public class ReentrantLockNote implements Lock, java.io.Serializable {
    private static final long serialVersionUID = 773984872572414699L;
    /** Synchronizer providing all implementation mechanics */
    private final ReentrantLockNote.Sync sync;
    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class Sync extends AQSNote {
        private static final long serialVersionUID = -5179523762034025860L;

        abstract void lock();
/*
* 尝试获取非公平锁
* 如果没人占用，则直接获取
* 如果占用着是当前线程，则说明锁重入，设置state为相应的计数
* 否则获取失败
* */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
//            log.info("state状态:" + c);
            if (c == 0) {//如果c==0说明此刻无人占有锁
                if (compareAndSetState(0, acquires)) {//通过CAS设置state变量
                    log.infoToFile("CAS设置成功   state状态:"+getState());
                    setExclusiveOwnerThread(current);//设置独占线程为当前线程
                    return true;//返回true
                }
            }
            else if (current == getExclusiveOwnerThread()) {//c!=0,说明锁已被占用，如果锁被自己占用
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);//设置state为重入次数,此时当前线程已经获取了锁，其他线程都在阻塞队列中，所以不用用CAS设置
                log.infoToFile("重入锁   state状态:"+getState());
                return true;
            }
            log.infoToFile("获取锁失败 state状态:"+getState());
            return false;//如果c!=0&&锁被其他线程占用，返回false，
        }

        protected final boolean tryRelease(int releases) {
            log.infoToFile("尝试释放锁,state状态:"+getState());
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            log.infoToFile("释放完毕,state状态:"+getState());
            return free;
        }

        protected final boolean isHeldExclusively() {
            // While we must in general read state before owner,
            // we don't need to do so to check if current thread is owner
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
        // Methods relayed from outer class

        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }

        /**
         * Reconstitutes the instance from a stream (that is, deserializes it).
         */
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }

    static final class NonfairSync extends ReentrantLockNote.Sync {
        private static final long serialVersionUID = 7316153563782823691L;
        final void lock() {
            log.infoToFile("通过CAS设置state为1,state状态:"+getState());
            if (compareAndSetState(0, 1)) {
                log.infoToFile("通过CAS尝试设置state为1成功    state状态:"+getState());
                setExclusiveOwnerThread(Thread.currentThread());
            }
            else {
                log.infoToFile("CAS设置失败");
                acquire(1);
            }
        }

        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }


    static final class FairSync extends ReentrantLockNote.Sync {
        private static final long serialVersionUID = -3000897897090466540L;

        final void lock() {
            acquire(1);
        }

        /*
        *尝试获取公平锁
        * 无人占有锁的时候，先判断队列中是否有其他节点
        * 如果有的话不获取锁
        *
        * 其他和非公平锁的逻辑一样
        *
        * 公平锁和非公平锁的区别在于第一次可以获取锁的时候
        * 公平锁会判断队列中是否有其他节点
        * 非公平锁是直接尝试去获取
        * */
        protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (!hasQueuedPredecessors() &&
                        compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
    }

    public ReentrantLockNote() {
        sync = new ReentrantLockNote.NonfairSync();
    }

    public ReentrantLockNote(boolean fair) {
        sync = fair ? new ReentrantLockNote.FairSync() : new ReentrantLockNote.NonfairSync();
    }

    public void lock() {
//        log.info("调用sync.lock()");
        sync.lock();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock() {
        return sync.nonfairTryAcquire(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    public void unlock() {
        log.infoToFile("unlock方法调用");
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public int getHoldCount() {
        return sync.getHoldCount();
    }

    public boolean isHeldByCurrentThread() {
        return sync.isHeldExclusively();
    }

    public boolean isLocked() {
        return sync.isLocked();
    }

    public final boolean isFair() {
        return sync instanceof ReentrantLockNote.FairSync;
    }
    protected Thread getOwner() {
        return sync.getOwner();
    }
    public final boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
    public final boolean hasQueuedThread(Thread thread) {
        return sync.isQueued(thread);
    }
    public final int getQueueLength() {
        return sync.getQueueLength();
    }
    protected Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }
    public boolean hasWaiters(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.hasWaiters((AQSNote.ConditionObject)condition);
    }
    public int getWaitQueueLength(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.getWaitQueueLength((AQSNote.ConditionObject)condition);
    }
    protected Collection<Thread> getWaitingThreads(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.getWaitingThreads((AQSNote.ConditionObject)condition);
    }
    public String toString() {
        Thread o = sync.getOwner();
        return super.toString() + ((o == null) ?
                "[Unlocked]" :
                "[Locked by thread " + o.getName() + "]");
    }
}

