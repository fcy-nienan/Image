package com.fcy.Java.Thread;

import lombok.Setter;

import java.util.concurrent.locks.LockSupport;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-06  16:52
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        runThread runThread=new runThread();
        runThread.start();
        Thread.sleep(5000);
        runThread=null;
//        runThread.setFlag(false);
    }
    static class runThread extends Thread{
        @Setter
        private boolean flag=true;
        @Override
        public void run() {
            while(flag){
                System.out.println(Thread.currentThread().getId()+"running");
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println(Thread.currentThread().getId()+" stopped!");
        }
    }

}
