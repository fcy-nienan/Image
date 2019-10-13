package com.fcy.Java.DesignPattern.MultiThread.LeaderFollow;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:11
 */
//监听一组worker线程运行情况
public class CountMonitor implements Runnable{
    private Worker[] workers;
    private Thread t;
    private long sleepTime=5000;
    public CountMonitor(Worker[] workers){
        this.workers=workers;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int blocked=0;
            int runnable=0;
            int leader=0;
            int follow=0;
            for(int i=0;i<workers.length;i++){
                Worker worker=workers[i];
                switch (worker.getState()){
                    case RUNNABLE:{
                        System.out.println(worker.getName()+"is running");
                        runnable++;
                        break;
                    }
                    case BLOCKED:{
                        blocked++;
                        break;
                    }
                }
                switch (worker.getOtherVisitState()){
                    case LEADER:{
                        leader++;
                        break;
                    }
                    case FOLLOW:{
                        follow++;
                        break;
                    }
                }
            }
            System.out.println("BLOCKED:"+blocked+"---RUNNABLE"+runnable);
            System.out.println("LEADER:"+leader+"---FOLLOW"+follow);
        }
    }
}
