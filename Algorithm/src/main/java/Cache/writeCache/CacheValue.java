package Cache.writeCache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.logging.Logger;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CacheValue {
    private static Logger logger = Logger.getLogger(CacheValue.class.getName());
    private String key;
    private String value;
    //上次访问时间
    private long lastAccess=0;
    //存活时间
    private long liveMillis=0;
    public CacheValue(String value){
        this.value=value;
    }

}
