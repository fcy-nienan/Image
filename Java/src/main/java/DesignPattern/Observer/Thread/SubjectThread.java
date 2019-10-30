package DesignPattern.Observer.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SubjectThread{
    private volatile int state=0;
    private CyclicBarrier barrier;
    private volatile boolean flag=false;
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    private List<ObserverThread> all=new ArrayList<>();
    public void addObserver(ObserverThread observerThread){
        observerThread.setSubjectThread(this);
        all.add(observerThread);
    }
    public void remove(ObserverThread observerThread){
        if(all.contains(observerThread)){
            all.remove(observerThread);
        }
    }
    public void reset(){
        String id=Thread.currentThread().getName();
        try {
            barrier.await();
            System.out.print(id+":seted"+barrier.getParties());
            this.flag=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public void change(int state){
        synchronized (this) {
            System.out.println("更改状态，通知观察者线程!");
            this.state = state;
            this.flag=true;
            barrier=new CyclicBarrier(all.size());
            this.notifyAll();
        }
    }
    public int getState(){
        return state;
    }
}
