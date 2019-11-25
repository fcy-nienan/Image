package Simulating;

import Simulating.PureMode.Request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class NetManager implements Runnable{
    private static Logger logger = Logger.getLogger(NetManager.class.getName());
    private Net[] nets;
    private BlockingQueue<Request> requestList=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true){
            try {
                Request request=requestList.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void send(Request request){

    }
//    the boolean represent the request is success or failure
    public boolean receive(Request request){
        return requestList.offer(request);
    }
}
