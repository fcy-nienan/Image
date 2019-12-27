package Advanced.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
//当所有线程运行结束后内存是会被释放掉的，不管是否能引用到那个data对象,因为GCROOT是只找能够引用的
//对象,然后清除其他区域,所以下面的写法是不会造成内存泄漏的
// 但是注意,在某个线程内部,当local被赋值为null的时候,这时data申请的内存并没有被释放掉
//因为他被Entry引用了，而Thread->ThreadMap->Entry[]->Entry->data
//这条引用链使得data不会被回收掉
//也就是ThreadLocal中设置的变量生命周期大于threadLocal的生命周期
//但为啥要这样设计呢？
public class DemoWeakReferenceMemoryLeak {
    public static void main(String args[]) throws Exception {
        for (int i=0;i<10;i++) {
            thread thread = new thread();
            thread.start();
        }
        Thread.sleep(1000);
        System.gc();
        try {
            new data();
        }catch (Throwable e){
            e.printStackTrace();
        }
        Thread.sleep(1000000000);
    }
    static class thread extends Thread{
        @Override
        public void run() {
            ThreadLocal<data> local=new ThreadLocal<>();
            System.out.println("start");
            try {
                Thread.sleep(12000);
                local.set(new data());
                local=null;
                System.gc();
            }catch (Throwable e){
                e.printStackTrace();
            }
            System.out.println("middle");
            try {
                Thread.sleep(63000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("finished");
        }
    }
    static class data{
        private byte[] bytes=new byte[1024*1024*88];
    }
}
