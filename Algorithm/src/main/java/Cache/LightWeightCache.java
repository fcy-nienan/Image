package Cache;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Logger;
/**
 * An entry in the cache is expired if
 * (1) it is added to the cache longer than the creation-expiration period, and
 * (2) it is not accessed for the access-expiration period.
 * When an entry is expired, it may be evicted from the cache.
 * When the size limit of the cache is set, the cache will evict the entries
 * with earliest expiration time, even if they are not expired.
 *
 * It is guaranteed that number of entries in the cache is less than or equal
 * to the size limit.  However, It is not guaranteed that expired entries are
 * evicted from the cache. An expired entry may possibly be accessed after its
 * expiration time. In such case, the expiration time may be updated.
 * This class does not support null entry
 * This class is not thread safe.
 */
public class LightWeightCache<K,E extends K> {
    private static Logger logger = Logger.getLogger(LightWeightCache.class.getName());
    private static long sum=0;
    private static void parallel(Spliterator spliterator){
        if (spliterator.getExactSizeIfKnown()>100000){
            parallel(spliterator.trySplit());
            parallel(spliterator);
        }else{
            new calculate(spliterator,e->sum+=(int)e).start();
        }
    }
    public static void main(String args[]) throws Exception {
        List list=new ArrayList();
        for (int i=0;i<1200000;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        long s=System.currentTimeMillis();
        System.out.println("start :"+s);
        list.forEach(e->sum+=(int)e);
        System.out.println("cost time:"+(System.currentTimeMillis()-s));
        Spliterator spliterator = list.spliterator();
        s=System.currentTimeMillis();
        parallel(spliterator);
        System.out.println("cost time:"+(System.currentTimeMillis()-s));
        Thread.sleep(10000);
        System.out.println(sum);
    }
    public static class calculate extends Thread{
        private static AtomicInteger integer=new AtomicInteger(0);
        private Spliterator<Integer> spliterator;
        private Consumer action=System.out::println;
        public calculate(Spliterator<Integer> spliterator, Consumer consumer){
            this.spliterator=spliterator;
            this.action=consumer;
        }
        @Override
        public void run() {
            int order=integer.addAndGet(1);
            System.out.println("thread :"+order+":size:"+spliterator.getExactSizeIfKnown());
            if (spliterator!=null){
                spliterator.forEachRemaining(this.action);
            }
        }
    }
}
