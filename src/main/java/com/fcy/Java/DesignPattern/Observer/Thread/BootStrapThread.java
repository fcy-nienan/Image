package com.fcy.Java.DesignPattern.Observer.Thread;

<<<<<<< HEAD
import com.fcy.DesignPattern.Observer.Thread.ObserverThread;
import com.fcy.DesignPattern.Observer.Thread.SubjectThread;

=======
>>>>>>> 4bdc1c1e1debb71d9dff9d6ef70e2ecb0e3d059f
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BootStrapThread {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();

        SubjectThread subjectThread=new SubjectThread();
        ObserverThread t1=new ObserverThread();
        ObserverThread t2=new ObserverThread();
        ObserverThread t3=new ObserverThread();

        subjectThread.addObserver(t1);
        subjectThread.addObserver(t2);
        subjectThread.addObserver(t3);

        service.submit(t1);
        service.submit(t2);
        service.submit(t3);


        /*
        * 线程池的submit线程不一定比主线程块，可能主线程都执行完了
        * 提交的线程才开始执行
        * */
        /*
        * 需要等待三个观察者线程都启动并且都阻塞在subjectThread上
        * 要不能可能会发生发布者线程已经通知了而观察者线程获取到state为false
        * 然后再次阻塞在发布者对象上
        * */
        Thread.sleep(3000);


        subjectThread.change(10);
        Thread.sleep(5000);
        subjectThread.change(20);
    }
}
