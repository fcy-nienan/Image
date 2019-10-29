package Concurrent.Thread;

import java.text.SimpleDateFormat;

/*
* 测试ThreadLocal和直接new一个对象花费时间
* */
public class ThreadLocalVariableDemo {
    static ThreadLocal<SimpleDateFormat> local=ThreadLocal.withInitial(ThreadLocalVariableDemo::init);
    static ThreadLocal<String> localId=ThreadLocal.withInitial(ThreadLocalVariableDemo::create);
    static ThreadLocal<Long> localInt=ThreadLocal.withInitial(ThreadLocalVariableDemo::createLong);
    public static String create(){return Thread.currentThread().getName();}
    public static Long createLong(){return Thread.currentThread().getId();}
    public static SimpleDateFormat init(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }
    public static void main(String[] args) {
        System.out.println(localInt.get());
        System.out.println(localInt.get());

        System.out.println(localId.get());
        System.out.println(localId.get());

        System.out.println(local.get());
        System.out.println(local.get());
        Thread thread= Thread.currentThread();
        System.out.println("kk");
    }
}
