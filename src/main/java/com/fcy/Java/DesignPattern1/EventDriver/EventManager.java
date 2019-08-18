package com.fcy.Java.DesignPattern1.EventDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  17:50
 */
public class EventManager {
    private Queue<Event> queue=new LinkedList();
    public void registry(Event event){
        queue.offer(event);
    }
    public Event getNext(){
        return queue.poll();
    }
}
