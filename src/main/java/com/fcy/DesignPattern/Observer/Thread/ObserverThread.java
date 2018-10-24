package com.fcy.DesignPattern.Observer.Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ObserverThread implements Runnable{
    private SubjectThread subjectThread;
    public void setSubjectThread(SubjectThread subjectThread){
        this.subjectThread=subjectThread;
    }
    @Override
    public void run() {
        while(true){
            if(subjectThread!=null){
                boolean flag=subjectThread.isFlag();
                if(flag){
                    int state=subjectThread.getState();
                    switch (state){
                        case 1:{
                            System.out.println(Thread.currentThread().getName()+"subject is changed to 1");
                            break;
                        }
                        case 2:{
                            System.out.println(Thread.currentThread().getName()+"subject is changed to 2");
                            break;
                        }
                        default:{
                            System.out.println(Thread.currentThread().getName()+"subject is changed to "+state);
                            break;
                        }
                    }
                    subjectThread.reset();
                }else{
                    synchronized (subjectThread) {
                        try {
                            System.out.println("observer blocked!");
                            subjectThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
