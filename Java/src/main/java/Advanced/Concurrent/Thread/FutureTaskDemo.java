package Advanced.Concurrent.Thread;

import ThreadUtil.TP;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
/*
* 未来对象模式
*
* */
public class FutureTaskDemo {
    public static void main (String args[]) {
        ThreadPoolExecutor tp= TP.getTPE();
        Future<?> submit = tp.submit(() -> {

        });

    }
}
