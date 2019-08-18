package com.fcy.Java.DesignPattern.MultiThread.MS;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-29  1:15
 */
public class Master {
    private BlockingQueue<Task> tasks=new LinkedBlockingQueue<>();
    private ConcurrentHashMap<String,Object> result=new ConcurrentHashMap<>();
    private HashMap<String,Thread> workerHashMap=new HashMap<>();
    public Master(Worker worker,int count){
        worker.setResultMap(result);
        worker.setTaskConcurrentHashMap(tasks);
        for(int i=0;i<count;i++){
            workerHashMap.put("worker"+i,new Thread(worker));
        }
    }
    public void add(Task task){
        tasks.add(task);
    }
    public void start(){
        workerHashMap.entrySet().forEach(e->{
            e.getValue().start();
        });
    }
    public boolean isCompleted(){
        for (Map.Entry<String, Thread> entry : workerHashMap.entrySet()) {
            if (entry.getValue().getState()!= Thread.State.TERMINATED)
                return false;
        }
        return true;
    }
    public int getResult(){
        int sum=0;
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            sum += (Integer) ((entry.getValue()));
        }
        return sum;
    }
}
