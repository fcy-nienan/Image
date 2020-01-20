package Cache.writeCache;

import java.util.HashMap;
import java.util.logging.Logger;

public class HardStorage extends HashMap<String,CacheValue> implements ExternalStorage{
    private static Logger logger = Logger.getLogger(HardStorage.class.getName());


}
