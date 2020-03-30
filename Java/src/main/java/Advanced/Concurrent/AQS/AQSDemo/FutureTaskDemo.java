package Advanced.Concurrent.AQS.AQSDemo;

import java.util.concurrent.*;

/*
* 该对象的模式就是提交的时候
* RunnableFuture<Void> ftask = newTaskFor(task, null);
* execute(ftask);
* 实际提交给线程池是这个Future对象
* Future对象相当于将run方法包装了一下
* 执行Future的run方法的时候会设置一系列的状态判断标志是否执行完成
* Future这个就是线程正在执行的方法所属的对象
* 将这个对象交给用户,所以叫未来对象也挺合理的
* 通过该对象的其他方法比如get,isDone,isCancelled等方法呈现响应的状态
* */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue queue=new SynchronousQueue();
        System.out.println(queue.offer("kk"));
        System.out.println(queue.offer("kk"));
        ConcurrentLinkedQueue linkedQueue;

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ExecutorService service=Executors.newCachedThreadPool();
        Future task=service.submit(runnable);
        FutureTask task1;
        Thread.sleep(3000);
        System.out.println(task.isDone());
        System.out.println(task.isCancelled());
        task.cancel(false);
        System.out.println(task.isCancelled());
        /*
         * Future获取线程的执行结果，调用cancel方法不会使线程暂停，
         * 只会将线程的中断标志位设置为true，如果线程调用了sleep，wait或join
         * 并且置该方法的参数为true，
         * 则会抛出InterruptException异常
         * 否则什么也不做
         *
         *
         * */

    }
}
