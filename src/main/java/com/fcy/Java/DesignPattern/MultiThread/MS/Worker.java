package com.fcy.Java.DesignPattern.MultiThread.MS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-29  1:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Worker implements Runnable{
    private BlockingQueue<Task> taskConcurrentHashMap;
    private ConcurrentHashMap<String,Object> resultMap;
    @Override
    public void run() {
        while(true){
            Task task=taskConcurrentHashMap.poll();
            if (task==null)break;
            Object result=handle(task);
            resultMap.put(task.getName(),result);
        }
    }
    public Object handle(Task task){
        return task.getResult();
    }
}
