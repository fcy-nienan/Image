package com.fcy.Java.Concurrent.Thread;

public class ThreadInterruptDemo {
    public static int y;
    public int x;
    class test{
        public test(){
            x=12;
            y=1;
        }
    }
    static class test1{
        public int kkk;
        public test1(){
            y=23;
        }
    }
    static class run1 implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                doSomeThing();
            }
        }
        private void doSomeThing(){}
    }
    public void test(){
        y=23;
    }
    public static void main(String[] args) throws InterruptedException {
//        testMultiInterrupt();
        note();
    }
    public static void innerStatic(){
        /*
         * 内部类和静态内部类
         *   内部类需要依赖外部类的实例来创建对象
         *   内部类隐式地有一个执行外部类的引用
         *   静态内部类不需要依赖外部类的实例就可以直接创建对象
         *   这只是编译时的概念，当编译成功时就是两个不同的class文件
         * */
        ThreadInterruptDemo demo=new ThreadInterruptDemo();
        test t=demo.new test();
        test1 t1=new ThreadInterruptDemo.test1();

        System.out.println(ThreadInterruptDemo.y);
        System.out.println(demo.x);
    }
    public static void note() throws InterruptedException {
        Thread t=new Thread();
        //中断状态默认为false，清除中断状态意味着 如果中断状态为true，则改为false
//                                         如果中断状态为false 则改为false
//        Thread.interrupted();//返回中断状态并清除中断状态
//        t.isInterrupted();//返回中断状态
//        以上两个方法都调用了private native boolean isInterrupted(boolean ClearInterrupted);
//        本地方法，参数标识是否清除中断状态
//        t.interrupt();//置中断状态为true
        Thread t1=new Thread(()->{
            Thread ttt=Thread.currentThread();
            ttt.isInterrupted();
            while(!ttt.isInterrupted()){

            }
            System.out.println("Interrupted!"+ttt.isInterrupted());
        });
        System.out.println(t1.isInterrupted());
        t1.start();
        System.out.println(t1.isInterrupted());
        Thread.sleep(4000);
        t1.interrupt();
        System.out.println(t1.isInterrupted());
//
//
//        System.out.println(t.isInterrupted());
//        t.interrupt();
//        System.out.println(t.isInterrupted());
//        Thread.interrupted();
//        System.out.println(t.isInterrupted());
    }
    public static void testMultiInterrupt() throws InterruptedException {
        Thread t=new Thread(new MultiInterrupt());
        System.out.println(t.isInterrupted());
        t.start();
        Thread.sleep(2000);
        t.interrupt();
        System.out.println("Main:"+t.isInterrupted());
        Thread.sleep(2000);
        System.out.println("Main:"+t.isInterrupted());
        t.interrupt();

    }
    static class MultiInterrupt implements Runnable {
        @Override
        public void run() {
            long id=Thread.currentThread().getId();
            System.out.println(id+":Start!");
            System.out.println(Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {//抛出异常后,JVM会把这个标志位设置为false
                System.out.println(id+":第一次中断!"+Thread.currentThread().isInterrupted());
            }
            System.out.println(id+":第一次中断后!"+Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                System.out.println(id+":第二次中断!");
            }
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }
}
