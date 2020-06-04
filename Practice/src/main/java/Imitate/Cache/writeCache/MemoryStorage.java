package Imitate.Cache.writeCache;

import java.util.HashMap;
import java.util.logging.Logger;

public class MemoryStorage extends HashMap<String,CacheValue> implements ExternalStorage{
    private static Logger logger = Logger.getLogger(MemoryStorage.class.getName());


}
