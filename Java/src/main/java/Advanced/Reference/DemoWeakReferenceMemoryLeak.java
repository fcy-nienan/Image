package Advanced.Reference;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DemoWeakReferenceMemoryLeak {
    public static void main(String args[]) throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(30,40,0, TimeUnit.SECONDS,new ArrayBlockingQueue(10));
        for (int i=0;i<12;i++) {
            executor.submit(()->{
                ThreadLocal<data> local=new ThreadLocal<>();
                System.out.println("start");
                try {
                    local.set(new data());
                    local=null;
                    System.gc();
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("middle");
                try {
                    Thread.sleep(13000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("finished");
            });
        }
    }
    static class data{
        private byte[] bytes=new byte[1024*1024*88];
    }
}
