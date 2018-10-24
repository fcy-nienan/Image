package com.fcy.Thread;

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
    public void test(){
        y=23;
    }
    public static void main(String[] args) {
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
    public static void note(){
        Thread t=new Thread();
        //中断状态默认为false，清除中断状态意味着 如果中断状态为true，则改为false
//                                         如果中断状态为false 则改为false
        Thread.interrupted();//返回中断状态并清除中断状态
        t.isInterrupted();//返回中断状态
//        以上两个方法都调用了private native boolean isInterrupted(boolean ClearInterrupted);
//        本地方法，参数标识是否清除中断状态
        t.interrupt();//置中断状态为true
    }
}
