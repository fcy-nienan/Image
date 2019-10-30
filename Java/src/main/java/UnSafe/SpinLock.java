package UnSafe;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/*
* 自定义自旋锁
* */
public class SpinLock {
    private static AtomicReference<Thread> signal=new AtomicReference<>();
    private static AtomicLong integer=new AtomicLong();
    private static int x=0;
    public static void lock(){
        Thread t=Thread.currentThread();
        while(!signal.compareAndSet(null,t)){

        }
    }
    public static void unlock(){
        Thread t=Thread.currentThread();
        signal.compareAndSet(t,null);
    }
    public static void intlock(){
        long id=Thread.currentThread().getId();
        String name=Thread.currentThread().getName();
        while(!integer.compareAndSet(0,id)){
        }
    }
    public static void intunlock(){
        long id=Thread.currentThread().getId();
        String name=Thread.currentThread().getName();
        while(!integer.compareAndSet(id,0)){
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<9;i++){
            Thread t=new Thread(new test());
            t.start();
//            t.join();
        }
        Thread.sleep(3000);
        System.out.println(x);
    }
    static class test implements Runnable{
        @Override
        public void run() {
            intlock();
            for(int i=0;i<1000;i++){
//                lock();
                x++;
//                unlock();
            }
            intunlock();
        }
    }
}
