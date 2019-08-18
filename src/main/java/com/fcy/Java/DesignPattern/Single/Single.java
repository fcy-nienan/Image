package com.fcy.Java.DesignPattern.Single;

public class Single {
    private static Single single;
//    最简单的单例模式,多线程不安全
    public static Single getSingle(){
        if(single==null)
            single=new Single();
        return single;
    }
//    多线程安全,但每次获取都需要去获取锁
    public static Single getSingle1(){
        synchronized(Single.class){
            if(single==null){
                single=new Single();
            }
        }
        return single;
    }
//    多线程不一定安全,原理同下
    public static Single getSingle2(){
        if(single==null){
            synchronized (Single.class){
                single=new Single();
            }
        }
        return single;
    }
//    该方法在多线程环境下仍然是不安全的
//    new一个对象包含多步操作,可能在一个线程中new到一半然后结束,另一个线程读取发现single不为null,则只拿到了半个对象
//    如果加上volatile关键字就可以了,读取的时候是直接从主存中读取的
    public static Single getSingle3(){
        if(single==null){
            synchronized(Single.class){
                if(single==null){
                    single=new Single();
                }
            }
        }
        return single;
    }
}
