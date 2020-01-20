package Cache.writeCache;

import org.slf4j.event.Level;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class CacheSystem {
    private static Logger logger = Logger.getLogger(CacheSystem.class.getName());
    private Cache cache;
    private ExternalStorage storage;
    public static int sizeLimit=0;
    public static long EvictMillis=10000;
    public CacheSystem(Cache cache,ExternalStorage externalStorage){
        this.cache=cache;
        this.storage=externalStorage;
        EvictExpired evictExpired = new EvictExpired(EvictMillis);
        Thread t=new Thread(evictExpired);
        t.start();
    }
    public String get(String key){
        return cache.get(key).getValue();
    }
    public String set(String key,String value){
        return cache.put(key,new CacheValue(value)).getValue();
    }
    public String set(String key,String value,long liveMillis){
        CacheValue cacheValue=new CacheValue();
        cacheValue.setKey(key);
        cacheValue.setValue(value);
        cacheValue.setLiveMillis(liveMillis);
        cache.put(key,cacheValue);
        return cacheValue.getValue();
    }
    public String remove(String key){
        return cache.remove(key).getValue();
    }
    private Iterator<Map.Entry<String,CacheValue>> iterator(){
        return cache.entrySet().iterator();
    }
    private boolean expired(String key){
        CacheValue value = cache.get(key);
        return expired(value);
    }
    private boolean expired(CacheValue value){
        long expiredMillis=value.getLastAccess()+value.getLiveMillis();
        if (java.lang.System.currentTimeMillis()>expiredMillis){
            return true;
        }
        return false;
    }
    class EvictExpired implements Runnable{
        private long evictPeriod;
        public EvictExpired(long evictPeriod){
            this.evictPeriod=evictPeriod;
        }
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(evictPeriod);
                } catch (InterruptedException e) {
                    logger.warning("EvictExpired has been interrupted !");
                    return;
                }
                Iterator<Map.Entry<String,CacheValue>> iterator=iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,CacheValue> entry=iterator.next();
                    String key=entry.getKey();
                    CacheValue value=entry.getValue();
                    if (expired(key)){
                        remove(key);
                        logger.info("expired key :"+key+" and remove it !");
                    }
                }
            }
        }
    }
}
