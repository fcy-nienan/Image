package SystemAndLog;

import ThreadUtil.TP;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class SystemAndLogTest {
    private static Logger logger = Logger.getLogger(SystemAndLogTest.class.getName());

    public static void main(String args[]) throws Exception {
        ThreadPoolExecutor tp=TP.getTPE();
        for (int i=0;i<10;i++){
            Future<Long> submit = tp.submit(new SystemThread());
        }

    }
}
