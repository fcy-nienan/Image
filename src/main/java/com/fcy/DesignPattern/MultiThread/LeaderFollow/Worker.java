package com.fcy.DesignPattern.MultiThread.LeaderFollow;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-26  23:30
 */
public class Worker implements Runnable{
    public enum State{
        LEADER,FOLLOW,PROCESSING;
    }
    private Thread t;
    private static Object mutex=new Object();
    private Object event;
    private static ThreadLocal<State> threadLocal=new ThreadLocal<>();
    private String name;
    private State otherVisitState;
    public Worker(String name){
        this.name=name;
        t=new Thread(this,this.name);
        t.start();
    }
    public Thread.State getState(){
        return t.getState();
    }
    public String getName(){
        return this.name;
    }
    public State getOtherVisitState(){
        return this.otherVisitState;
    }
    @Override
    public void run() {
        threadLocal.set(State.FOLLOW);
        otherVisitState=State.FOLLOW;
        while(true){
          toLeader();
          toProcess();
          dealEvent(event);
          toFollow();
      }
    }
    public void toFollow(){
        threadLocal.set(State.FOLLOW);
        otherVisitState=State.FOLLOW;
    }
    public void toProcess(){
        threadLocal.set(State.PROCESSING);
        otherVisitState=State.PROCESSING;
    }
    public void dealEvent(Object event){
        int size=((EventProducer.HandlerEvent)event).handler();
    }
    public void toLeader(){
        synchronized (mutex) {
            State state = threadLocal.get();
            if (state == State.LEADER) {
                return;
            } else if (state == State.FOLLOW) {
                threadLocal.set(State.LEADER);
                otherVisitState=State.LEADER;
            }
            event=EventProducer.accept();
            mutex.notify();
        }
    }
}
