package com.fcy.Java.Concurrent.AQS.AQSDemo;

import java.util.concurrent.*;

public class ExchangeDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t;
        Exchanger<String> exchanger=new Exchanger<>();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(10,20,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        ThreadA a=new ThreadA(exchanger,"Hello AA",0);
        ThreadA b=new ThreadA(exchanger,"Hello BB",3000);
        executor.submit(a);
        executor.submit(b);
        Thread.sleep(1000);
        executor.shutdown();
    }
    static class ThreadA implements Runnable{
        private Exchanger<String> exchanger;
        private String data;
        private String oldData;
        private long timeout;
        public ThreadA(Exchanger exchanger,String data,long timeout){
            this.exchanger=exchanger;
            this.data=data;
            this.timeout=timeout;
        }
        @Override
        public void run() {
            System.out.println("start exchange data");
            try {
                Thread.sleep(timeout);
                this.oldData=data;
//                到达同步点，等待其他线程到来
                this.data=exchanger.exchange(data,2,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("data exchange finished");
            dis();
        }
        public void dis(){
            System.out.println("my source data :"+oldData+"---after exchange my data:"+data);
        }
    }

}
