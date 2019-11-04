package ThreadUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class TF implements ThreadFactory {
    private static Logger logger = Logger.getLogger(TF.class.getName());
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    public TF(String namePrefix,ThreadGroup group){
        this.group=group;
        this.namePrefix=namePrefix;
    }
    public TF(){
        this("fcy",Thread.currentThread().getThreadGroup());
    }
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(group,r
                ,namePrefix+"-"+
                poolNumber.getAndIncrement()+"-"+
                threadNumber.getAndIncrement()+"-");
    }
}
