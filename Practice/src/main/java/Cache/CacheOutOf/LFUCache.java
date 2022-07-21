package Cache.CacheOutOf;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;
/*
* 一、
*   堆排序+HashMap,Node节点记录使用频率
* 二、
*   HashMap<String,Node>
*   HashMap<Integer,LinkedList<Node>> 存储每个频次对应的链表
*   int max,int min;
* */

public class LFUCache {
    private static Logger logger = Logger.getLogger(LFUCache.class.getName());

    private HashMap<String,Node> cacheMap = new HashMap();
    private HashMap<Integer,LinkedList<Node>> timeMap = new HashMap<>();
    private int maxTime = 0;
    private int minTime = 0;
    private int capacity = 3;
    @Getter
    @Setter
    @AllArgsConstructor
    static class Node{
        private String key;
        private String value;
        private int times;
    }
    private LinkedList<Node> safe_get(int index){
        if (timeMap.get(index)==null){
            timeMap.put(index,new LinkedList<Node>());
        }
        return timeMap.get(index);
    }
    public void display(){
        cacheMap.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + ":" + entry.getValue().value);
        });
        timeMap.entrySet().forEach(entry -> {
            System.out.println("-------" + entry.getKey() + "--------");
            entry.getValue().iterator().forEachRemaining(node->{
                System.out.println(node.key + ":" + node.value + ":");
            });
        });
    }
    public void set(String key,String value){
        Node node = cacheMap.get(key);
        if (node != null){
            visit(node);
        }else{
            if (cacheMap.size() == capacity){
                LinkedList<Node> minLinked = safe_get(minTime);
                Node lastNode = minLinked.getLast();
                cacheMap.remove(lastNode.key);
                minLinked.removeLast();
                logger.warning("淘汰缓存:" + lastNode.key + ":" + lastNode.value + ":" + lastNode.times);
            }
            Node newNode = new Node(key,value,1);
            cacheMap.put(key,newNode);
            safe_get(1).addFirst(newNode);
            minTime = 1;
        }
    }
    private void visit(Node node){
        LinkedList link = safe_get(node.times);
        link.remove(node);
        node.times ++;
        LinkedList newLink = safe_get(node.times);
        newLink.addFirst(node);
        if(node.times > maxTime){
            maxTime = node.times;
        }
        LinkedList<Node> minLink = safe_get(minTime);
        while(minLink.size()==0){
            minTime = minTime + 1;
            minLink = safe_get(minTime);
        }
    }
    public String get(String key){
        Node node = cacheMap.get(key);
        String return_value = null;
        if (node != null){
            return_value = node.value;
            visit(node);
        }
        return return_value;
    }

    public static void main(String args[]) throws Exception {
        LFUCache lfuCache = new LFUCache();
        lfuCache.set("1","1");
        lfuCache.set("1","1");
        lfuCache.set("2","2");
        lfuCache.set("2","2");
        lfuCache.set("3","3");
        lfuCache.set("3","3");
        lfuCache.set("4","4");
        lfuCache.get("1");
        lfuCache.display();
    }
}
