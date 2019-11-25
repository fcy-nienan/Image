package Simulating;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Computer implements Runnable{
    private Hardware[] hardwares;
    private Memory[] memories;
    private Net[] nets;
    private ThreadPoolExecutor executor=new ThreadPoolExecutor(20,30,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
    private static Logger logger = Logger.getLogger(Computer.class.getName());

    @Override
    public void run() {
        for (Hardware hardware : hardwares) {
            executor.submit(hardware);
        }
        for (Memory memory : memories) {
            executor.submit(memory);
        }
        for (Net net : nets) {
            executor.submit(net);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
