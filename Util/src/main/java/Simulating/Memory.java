package Simulating;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Memory implements Runnable{
    private static Logger logger = Logger.getLogger(Memory.class.getName());
    private static final String startMsg="memory process is running....";
    private Map<Object,Object> memory=new HashMap<Object, Object>();
    public static void main(String args[]) throws Exception {

    }

    @Override
    public void run() {
        logger.info(startMsg);

    }
    public Object put(Object key,Object value){
        return memory.put(key, value);
    }
    public Object remove(Object key,Object value){
        return memory.remove(key);
    }
}
