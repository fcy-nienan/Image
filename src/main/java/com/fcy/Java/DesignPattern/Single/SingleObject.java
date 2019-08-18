package com.fcy.Java.DesignPattern.Single;

public class SingleObject {
    private static Object obj;
    public static Object getInstance(){
        if(obj==null){
            obj=new Object();
        }
        return obj;
    }
    public static synchronized Object getObject(){
        if(obj==null)
            obj=new Object();
        return obj;
    }
    public static Object getO(){
        if(obj==null){
            synchronized (SingleObject.class){
                obj=new Object();
            }
        }
        return obj;
    }
    //线程A、B同时执行if判断此时两个线程都进入同步块
//    在某一个线程执行完毕同步块的时候，另一个线程也会进入，此时object已经实例化了
//    如果没有里面的if判断则又会new一个对象，使得原来的引用失效

//    该方式也有缺陷，可能拿到一个无效的引用
    public static Object dcheck(){
        if(obj==null){
            synchronized (SingleObject.class){
                if(obj==null){
                    obj=new Object();
                }
            }
        }
        return obj;
    }
    private static volatile Object obj1;
//    双重锁机制
    public static Object doublecheck(){
        if(obj==null){
            synchronized (SingleObject.class){
                if(obj==null){
                    obj=new Object();
                }
            }
        }
        return obj;
    }
//    只有真正调用getInnerObject方法时才会加载并初始化该静态内部类
//    使用的是类初始化阶段保证只初始化一次
    /*
    * 内部类是和外部类同时存在的
    * 静态内部类并不是,它是可以单独存在了,只有在使用的时候才初始化
    * */
    public static Object getInnerObject(){
        return inner.instance;
    }
    static class inner{
//        final保证该对象不能再赋值了
        private static final Object instance=new Object();
    }

}
