package Concurrent.Thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadFactoryMain {
    private static int x=1;
    private int username=0;
    private static Object obj=new Object();
    public static void main(String[] args) throws Exception {
//        testReentryLock();
        testUnsafe();

    }
    public static void testUnsafe() throws Exception {
        //通过反射获取Unsafe对象  该对象不能通过getUnsafe获取  此方法会检测调用getUnsafe方法的调用者的类加载器是否是由bootstrap类
//        加载器加载  如果是 则可以调用  否则抛出SecruityException异常
        Field field=Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        final Unsafe unsafe= (Unsafe) field.get(null);//底层字段是一个static类型  则obj为null  否则为实例字段为对应的object对象

//        不知道怎么回事  静态字段老是出错
        ThreadFactoryMain main=new ThreadFactoryMain();
//        ThreadFactoryMain main= (ThreadFactoryMain) unsafe.allocateInstance(ThreadFactoryMain.class);
        Field f=main.getClass().getDeclaredField("username");
        long usernameOffset=unsafe.objectFieldOffset(f);
        unsafe.compareAndSwapInt(main,usernameOffset,0,2);
        System.out.println(ThreadFactoryMain.x);
        System.out.println(usernameOffset);
        Thread t1=new Thread(()->{
            for(int i=0;i<10000;i++){
                int var;
                do{//CAS失败重试
                    //var=unsafe.getIntVolatile(main,usernameOffset);
                }while(!unsafe.compareAndSwapInt(main,usernameOffset,main.username,main.username+1));
                /*int var=unsafe.getIntVolatile(main,usernameOffset);
                unsafe.compareAndSwapInt(main,usernameOffset,var,var+1);*/
//                unsafe.getAndAddInt(main,usernameOffset,1);
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10000;i++){
                int var;
                do {
                    var = unsafe.getIntVolatile(main, usernameOffset);
                }while(!unsafe.compareAndSwapInt(main, usernameOffset, main.username, main.username + 1));
//                unsafe.getAndAddInt(main,usernameOffset,1);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(main.username);
    }
    public static void testReentryLock()throws Exception{
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();
        Thread t1=new Thread(()->{
            for(int i=0;i<100000;i++){
                reentrantLock.lock();
                x++;
                if(x==600) {
                    try {
                        System.out.println("Thread 1"+x);
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                reentrantLock.unlock();
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<100000;i++){
                reentrantLock.lock();
                x++;
                if(x==9000) {
                    condition.signal();
                    System.out.println("Thread 2"+x);
                }
                reentrantLock.unlock();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(x);
    }
    public static boolean testGoto(){
        /*
        * break retry:会中断内部和外部的循环并回到retry处,并且不再进入循环而是直接运行循环后面的语句
        * continue retry:会中断内部和外部的循环并回到retry处,然后会再次进入循环
        * */
        int count=0;
        retry:
        for(;;){
            count++;
            if(count>100000)
                return true;
            System.out.println("kk");
            for(;;){
                count++;
                if(count>10000) {
                    System.out.println("break");
                    //continue retry;
                    break retry;
                }
            }
            //System.out.println("waimain");
            //return true;
        }
        System.out.println("kkk");
        return true;

    }
    public static void testGroup() throws InterruptedException {
        test t1=new ThreadFactoryMain.test();
        ThreadFactoryMain main=new ThreadFactoryMain();
        ThreadGroup group=new ThreadGroup("group");
        for(int i=0;i<10;i++){
            Thread t=new Thread(group,main.new task());
            t.start();
        }
        Thread.sleep(5000);
        group.list();
        System.out.println(group.activeCount());
    }
    class task implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("ThreadName:"+Thread.currentThread().getName()+"running");
            }
        }
    }
    static class test{

    }
}
