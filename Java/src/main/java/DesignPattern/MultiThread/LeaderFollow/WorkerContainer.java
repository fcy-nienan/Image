package DesignPattern.MultiThread.LeaderFollow;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-26  23:44
 */
public class WorkerContainer {
    public static void main(String[] args) {
        int count=10;
        Worker[] workers=new Worker[count];
        for(int i=0;i<count;i++){
            workers[i]=new Worker("name:"+i);
        }
        new CountMonitor(workers);
    }
}
