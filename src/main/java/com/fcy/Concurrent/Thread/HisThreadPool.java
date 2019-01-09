package com.fcy.Concurrent.Thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HisThreadPool {
    private LinkedList<Runnable> jober;
    private List<Thread> worker;
    private int current;
    public HisThreadPool(int size){
        worker=new ArrayList<>();
        jober=new LinkedList<>();
        for(int i=0;i<size;i++){
            Thread t=new Thread(()->{
                execute();
            });
            t.start();
            worker.add(t);
        }
    }
    public void showState(){
        worker.forEach(e->{
            System.out.println(e.getName()+":"+e.getState());
        });
        System.out.println("jober size:"+jober.size());
    }
    public void submit(Runnable runnable){
        synchronized (jober){
            jober.add(runnable);
            current++;
            jober.notify();
        }
    }
    public void execute(){
        while(true) {
            Runnable runnable = null;
            synchronized (jober) {
                try {
                    if(jober.size()==0)
                        jober.wait();
                    else{
                        runnable = jober.getLast();
                        jober.removeLast();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public static void main(String[] args) {
        HisThreadPool his=new HisThreadPool(3);
        Thread t1=new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i+"  "+Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=10;i<20;i++){
                System.out.println(i+"  "+Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        his.submit(t1);
        his.submit(t2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        his.showState();
        for(int i=0;i<10;i++){
            his.submit(t1);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        his.showState();
        Thread t;

    }
}
