package com.fcy.Concurrent.Thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-27  12:31
 */
public class ScheduleThreadExecutorDemo {
    public static void main(String args[]) {
//        testScheduleThread();
        testTimer();

    }
    public static void testScheduleThread(){
        ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(3);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("log");
            }
        },0,3000,TimeUnit.MILLISECONDS);
    }
    public static void testTimer(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("kk");
            }
        },3000,3000);
    }
}
