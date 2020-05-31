package CommonUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;
@Slf4j
public class StartWatch {
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
        log.debug(" cost time:"+costSec+"秒--"+costMil+"毫秒--"+costNano+"纳秒--"+msg);
        start=System.nanoTime();
    }
}
