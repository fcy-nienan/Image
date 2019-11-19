package SystemAndLog;

import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class SystemThread implements Callable<Long> {
    @Setter
    private int count;
    @Override
    public Long call() {
        long start=System.currentTimeMillis();
        for (int i=0;i<count;i++){
            System.out.println(i);
        }
        long end=System.currentTimeMillis();
        return end-start;
    }
}
