package ThreadUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TP {
    private static Logger logger = Logger.getLogger(TP.class.getName());
    private static ThreadPoolExecutor executor=new ThreadPoolExecutor(10000,20000,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new TF(),new ThreadPoolExecutor.DiscardPolicy());
    public static ThreadPoolExecutor getTPE(){
        return new ThreadPoolExecutor(10000,20000,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new TF(),new ThreadPoolExecutor.DiscardPolicy());
    }
}
