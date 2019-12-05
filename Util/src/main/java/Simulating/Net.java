package Simulating;

import Simulating.PureMode.IP;
import Simulating.PureMode.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class Net implements Runnable{
    private static Logger logger = Logger.getLogger(Net.class.getName());
    private IP[] ips;
    private BlockingQueue<Request> requestList=new LinkedBlockingDeque();
    private NetManager netManager;
    public Net() {
        super();
    }

    public boolean hit(IP ip){
        for (IP ip1 : ips) {
            if (ip1.equals(ip)){
                return true;
            }
        }
        return false;
    }
    public void receive(Request request)throws TimeoutException{
        if (!requestList.offer(request)) {
            throw new TimeoutException("请求超时!");
        }
    }
    public void send(Request request)throws TimeoutException {
        if (!netManager.receive(request)) {
            throw new TimeoutException("请求超时!");
        }
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        Thread.currentThread().getStackTrace();

    }
}
