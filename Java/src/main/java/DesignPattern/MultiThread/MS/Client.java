package DesignPattern.MultiThread.MS;

import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-29  1:30
 */
public class Client {
    public static void main(String[] args) {

        Master master=new Master(new Worker(),10);
        Random random=new Random();
        for(int i=0;i<100;i++){
            Task task=new Task();
            task.setName("task"+i);
            task.setResult(random.nextInt(100));
            master.add(task);
        }
        master.start();
        while(true){
            if (master.isCompleted()){
                System.out.println("result:"+master.getResult());
                break;
            }
        }
    }

}
