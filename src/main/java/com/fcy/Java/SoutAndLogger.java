package com.fcy.Java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class SoutAndLogger {
    private static Logger logger = Logger.getLogger(SoutAndLogger.class.getName());
    private static ThreadPoolExecutor executor;
    static {
        try{
            executor=new ThreadPoolExecutor(2000,3000,0, TimeUnit.SECONDS,new LinkedBlockingQueue());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws Exception {
        System.out.println("abcdefg".substring(2,4));
    }
    public static void testFutureList()throws  Exception{
        List<Future> futures=new ArrayList<>();
        int count=100;
        for(int i=0;i<count;i++){
            futures.add(executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("execute finished!\r");
                }
            }));
        }
        Thread.sleep(2000);
        while (true) {
            int resultCount = 0;
            for (Future future : futures) {
                if (!future.isCancelled() && future.isDone()) {
                    resultCount++;
                }else{
                    resultCount=0;
                    continue;
                }
            }
            if (resultCount==count){
                break;
            }
        }
        System.out.println("finished!");
    }
    public static void testFuture() throws InterruptedException, ExecutionException {
        Future future=executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("finished!");
            }
        });
        Thread.sleep(1000);
        System.out.println(future.isDone());//true
        System.out.println(future.isCancelled());//false
        System.out.println(future.get());//null
    }
    public static void testSpeed(){
        int outCount=5000;
        int threadCount=500;
        List<Future> futureList=new ArrayList<>();
        CyclicBarrier SystemBarrier=new CyclicBarrier(threadCount+1);
        CyclicBarrier LogBarrier=new CyclicBarrier(threadCount+1);
        for(int i=0;i<threadCount;i++){
            Future<?> submit = executor.submit(new SystemRecord(outCount,SystemBarrier));
            futureList.add(submit);
        }
        long SystemTime=waitThreadForTermiate(futureList,SystemBarrier);

        List<Future> recordList=new ArrayList<>();
        for(int i=0;i<threadCount;i++){
            Future submit=executor.submit(new LogRecord(outCount,LogBarrier));
            recordList.add(submit);
        }
        long LogTime=waitThreadForTermiate(recordList,LogBarrier);

        System.out.println("SystemTime:"+SystemTime);
        System.out.println("LogTime:"+LogTime);
    }
    private static long waitThreadForTermiate(List<Future> futures,CyclicBarrier barrier){
        int resultCount=futures.size();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        long start=System.currentTimeMillis();
        while (true){
            int collectCount=0;
            for (Future future:futures){
                if (!future.isCancelled()&&future.isDone()){
                    collectCount++;
                }else{
                    break;
                }
            }
//            下面这种写法会出现错误,可能最后几个任务执行完成了,此时collect>0,并且下次遍历在
//            原有的数值上增加,所以会一直执行不退出
//            for(Future future:futures){
//                if (!future.isCancelled()&&future.isDone()){
//                    collectCount++;
//                }else{
//                    System.out.print("collect:"+collectCount+"\r");
//                    collectCount=0;
//                    continue;
//                }
//            }
            if (collectCount==resultCount){
                break;
            }
        }
        long end=System.currentTimeMillis();
        return end-start;
    }
    static class LogRecord implements Runnable{
        private int count;
        private CyclicBarrier barrier;
        public LogRecord(int count,CyclicBarrier barrier){
            this.count=count;
            this.barrier=barrier;
        }
        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for(int i=0;i<count;i++){
                logger.info(+i+"\r");
            }
//            System.out.println();
        }
    }
    static class SystemRecord implements Runnable{
        private CyclicBarrier barrier;
        private int count;
        public SystemRecord(int count,CyclicBarrier barrier){
            this.count=count;
            this.barrier=barrier;
        }
        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for(int i=0;i<count;i++){
                System.out.print(Thread.currentThread().getName()+"十月 12, 2019 11:01:42 上午 com.fcy.Java.SoutAndLogger$LogRecord run" +
                        "信息: log:"+i+"\r");
            }
//            System.out.println();
        }
    }
}
