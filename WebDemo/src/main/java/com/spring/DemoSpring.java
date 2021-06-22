package com.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DemoSpring {
    private static Logger logger = Logger.getLogger(DemoSpring.class.getName());

    public static void main(String args[]) throws Exception{
//        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("application.xml");
//        Object beanPostProcess = applicationContext.getBean("beanPostProcess");
//        Object beanFactoryProcess = applicationContext.getBean("beanFactoryPostProcess");
//        Object user1 = applicationContext.getBean("user");
//        System.out.println(user1.toString());

        Thread t1=new Thread(new test());
        Thread t2=new Thread(new test1());
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        Thread.sleep(11113000);
        t2.start();
        Integer t=new Integer(12);
        Long l=new Long(12);
        Double d=new Double(12);
        Boolean bo=new Boolean(true);

    }
    static class test1 implements Runnable{
        @Override
        public void run() {
            enter();
        }
    }
    static class test implements Runnable{
        @Override
        public void run()  {
            enter();
        }
    }
    public static void enter(){
        synchronized (DemoSpring.class){
            String name = Thread.currentThread().getName();
            System.out.println(name+"线程开始执行!");
            System.out.println(name+"线程抛出异常");
            throw new NullPointerException("测试");
        }
    }
}
