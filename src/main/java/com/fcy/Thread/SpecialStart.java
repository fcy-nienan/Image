package com.fcy.Thread;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-22  23:29
 */
public class SpecialStart {
    public static void main(String[] args) {
        new task();
    }
    static class task implements Runnable{
        private Thread thread;
        public task(){
            thread=new Thread(this);
            thread.start();
        }
        @Override
        public void run() {
            while(true){
                System.out.println("RUNNING");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
