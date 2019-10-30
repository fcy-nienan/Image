package DesignPattern.MultiThread.LeaderFollow;

import java.util.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-26  23:31
 */
//worker线程需要去处理的任务
public class EventProducer {
    public static Object accept(){
        return new HandlerEvent();
    }
    static class HandlerEvent{
        public int handler(){
            Map<String,String> map=new HashMap();
            for(int i=0;i<100000;i++){
                map.put("key:"+i,"value:"+i);
            }
            Set<String> set=map.keySet();
            Iterator<String> iterable=set.iterator();
            StringBuilder builder=new StringBuilder();
            while(iterable.hasNext()){
                String key=iterable.next();
                String value=map.get(key);
                builder.append(value);
            }
            int size=builder.length();
            return size;
        }
    }
}
