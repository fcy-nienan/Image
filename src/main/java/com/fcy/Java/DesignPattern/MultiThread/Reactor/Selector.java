package com.fcy.Java.DesignPattern.MultiThread.Reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:37
 */
public class Selector {
    private BlockingQueue<Event> eventQueue=new LinkedBlockingQueue<>();
    private Object lock=new Object();

    public void addEvent(Event event){
        boolean success=eventQueue.offer(event);
        if (success){
            synchronized (lock){
                lock.notify();
            }
        }
    }
    public List<Event> select(){
        return select(0);
    }
    public List<Event> select(long time){
        if (time>0){
            if (eventQueue.isEmpty()){
                synchronized (lock){
                    if (eventQueue.isEmpty()){
                        try{
                            lock.wait();
                        }catch (InterruptedException e){}
                    }
                }
            }
        }
        List<Event> events=new ArrayList<>();
        eventQueue.drainTo(events);
        return events;
    }
}
