package com.fcy.Collection;

import java.util.HashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton: 基于DelayQueue实现的缓存类
 * @author: fcy
 * @date: 2018-08-27  11:25
 */
public class DelayQueueCache {
    private HashMap map=new HashMap();
    private DelayQueue cache=new DelayQueue();
    private Thread checkDemo;
    public DelayQueueCache(){
        this.checkDemo=new Thread(()->{
            check();
        });
        this.checkDemo.setDaemon(true);
        this.checkDemo.setName("Cache Check");
        this.checkDemo.start();
    }
    public void check(){
        System.out.println("Cache Started!");
        while(true){
            try {
                Object object=cache.take();
                if(object!=null){
                    DelayObject delayObject=(DelayObject)object;
                    map.remove(delayObject.key);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Cache Stoped!");
    }
    public void put(Object key,Object value,long time){
        Object object=map.put(key,value);
        //如果缓存中已有该元素说明延时队列也有，需要将延时队列中的元素移除
        if(object!=null){
            cache.remove(object);
        }
        cache.add(new DelayObject(key,time));
    }
    public Object get(Object key){
        return map.get(key);
    }
    public static class DelayObject implements Delayed {
        private long expireTime;
        private Object key;
        public DelayObject(Object key,long expire){
            this.key=key;
            this.expireTime=expire+System.currentTimeMillis();
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expireTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }
        @Override
        public int compareTo(Delayed o) {
            DelayObject object=(DelayObject)o;
            long apart=this.expireTime-object.expireTime;
            if(apart>0)
                return 1;
            else if (apart==0)
                return 0;
            else
                return -1;
        }
    }
    public static void main(String args[]) throws InterruptedException {
        DelayQueueCache cache=new DelayQueueCache();
        cache.put("name","fcy",5000);
        cache.put("password","123456",5000);
        cache.put("age",12,10000);

        System.out.println(cache.get("name"));
        System.out.println(cache.get("password"));
        System.out.println(cache.get("age"));
        Thread.sleep(6000);
        System.out.println(cache.get("name"));
        System.out.println(cache.get("password"));
        System.out.println(cache.get("age"));
        Thread.sleep(10000);
        System.out.println(cache.get("name"));
        System.out.println(cache.get("password"));
        System.out.println(cache.get("age"));
    }
}
