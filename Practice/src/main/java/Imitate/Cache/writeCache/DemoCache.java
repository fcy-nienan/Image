package Imitate.Cache.writeCache;

import java.util.Random;
import java.util.logging.Logger;

public class DemoCache {
    private static Logger logger = Logger.getLogger(DemoCache.class.getName());

    public static void main(String args[]) throws Exception {
        Cache cache=new Cache();
        ExternalStorage storage=new MemoryStorage();
        CacheSystem system=new CacheSystem(cache,storage);
        Random random=new Random();
        while(true){
            Thread.sleep(3000);
            system.set(String.valueOf(random.nextInt(200)),
                    String.valueOf(random.nextInt(5000)),
                    random.nextInt(20)*1000);
        }

    }
}
