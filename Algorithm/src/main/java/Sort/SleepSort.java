package com.fcy.Algorithm.Sort;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  20:12
 */
public class SleepSort {
    public static void main(String[] args) {
        int[] data=new int[]{8,3,0,5,1,6,3,9,2};
        for(int i=0;i<data.length;i++){
            new sort(data[i]);
        }
    }
    static class sort implements Runnable{
        private int x=0;
        private Thread t;
        public sort(int x){
            this.x=x;
            t=new Thread(this);
            t.start();
        }
        @Override
        public void run() {
            try {
                Thread.sleep(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(x);
        }
    }
}
