package com.fcy.Concurrent.AQS.AQSDemo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExchangeDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t;
        Exchanger<String> exchanger=new Exchanger<>();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(10,20,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        ThreadA a=new ThreadA(exchanger,"Hello AA");
        ThreadA b=new ThreadA(exchanger,"Hello BB");
        executor.submit(a);
        executor.submit(b);
        Thread.sleep(1000);
        a.dis();
        b.dis();
    }
    static class ThreadA implements Runnable{
        private Exchanger<String> exchanger;
        private String data;
        public ThreadA(Exchanger exchanger,String data){
            this.exchanger=exchanger;
            this.data=data;
        }
        @Override
        public void run() {
            System.out.println("start exchange data");
            try {
//                到达同步点，等待其他线程到来
                this.data=exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("data exchange finished");
        }
        public void dis(){
            System.out.println("this id my data:"+data);
        }
    }

}
