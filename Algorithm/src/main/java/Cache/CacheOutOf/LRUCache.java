package Cache.CacheOutOf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

public class LRUCache {
    private LinkedList<Entry> linkedList=new LinkedList();
    private HashMap<String,Entry> map=new HashMap();
    private int capacity;
    public LRUCache(int capacity){
        this.capacity=capacity;
    }
    static class Entry{
        private String key;
        private String value;
        public Entry(String key,String value){
            this.key=key;
            this.value=value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Entry){
                Entry o=(Entry)obj;
                if (o.key.equals(key)&&o.value.equals(value)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return key+":"+value;
        }
    }
    private static Logger logger = Logger.getLogger(LRUCache.class.getName());

    public String get(String key){
        Entry entry=map.get(key);
        if (entry==null){
            return null;
        }
        linkedList.remove(entry);
        linkedList.addFirst(entry);
        map.put(key,entry);
        return entry.value;
    }
    public String set(String key,String value){
        if (capacity==linkedList.size()){
            Entry entry= linkedList.removeLast();
            map.remove(entry.key);
            logger.warning("淘汰元素:"+entry.toString());
        }
        Entry entry = map.get(key);
        if (entry==null){
            entry=new Entry(key,value);
            linkedList.addFirst(entry);
            map.put(key,entry);
            return value;
        }else{
            linkedList.remove(entry);
            String oldValue=entry.value;
            entry.value=value;
            linkedList.addFirst(entry);
            map.put(key,entry);
            return oldValue;
        }
    }
    public void display(){
        Iterator<Entry> iterator = linkedList.iterator();
        StringBuilder builder=new StringBuilder();
        while (iterator.hasNext()){
            Entry next = iterator.next();
            builder.append(next.value+"->");
        }
        System.out.println(builder.toString());
    }
    public static void main(String args[]) throws Exception {
        String seq="1,2,3,2,1,2,3,4,2,2,1,2";
        LRUCache lruCache=new LRUCache(3);
        lruCache.set("1","1");
        lruCache.set("2","2");
        lruCache.set("1","1");
        lruCache.set("4","4");
        lruCache.set("5","5");
        lruCache.get("1");
        lruCache.display();
    }
}
