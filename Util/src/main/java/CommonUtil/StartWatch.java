package CommonUtil;

import java.util.logging.Logger;

public class StartWatch {
    private static Logger logger = Logger.getLogger(StartWatch.class.getName());
    private long start;
    private long end;
    public static StartWatch instance(){
        return new StartWatch();
    }
    public void init(){
        start=System.nanoTime();
    }
    public void cost(){
        cost("");
    }
    public void cost(String msg){
        end=System.nanoTime();
        long costNano=(end-start);
        long costMil=costNano/1000/1000;
        long costSec=costMil/1000;
        logger.info(" cost time:"+costSec+"秒--"+costMil+"毫秒--"+costNano+"纳秒--"+msg);
        start=System.nanoTime();
    }
}
