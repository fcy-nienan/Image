package SystemAndLog;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.logging.Logger;
@Slf4j
public class LogThread implements Callable<Long> {
    @Setter
    private int count;
    @Override
    public Long call() {
        long start=System.currentTimeMillis();
        for (int i=0;i<count;i++){
            log.info("{}",i);
        }
        long end=System.currentTimeMillis();
        return end-start;
    }
}
