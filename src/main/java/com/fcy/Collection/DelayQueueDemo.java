package com.fcy.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-27  0:10
 */
public class DelayQueueDemo {
    public static void main(String args[]) throws InterruptedException {
        testDelayQueue();
    }
    public static void testDelayQueue() throws InterruptedException {
        DelayQueue queue=new DelayQueue();
        for(int i=0;i<10;i++){
            queue.add(new user(5000,i,i+"fcy"));
        }
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.take());
    }
    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class user implements Delayed {
        private int id;
        private String name;
        private long delay;
        private long expire;
        public user(long delay,int id,String name){
            this.id=id;
            this.name=name;
            this.delay=delay;
            this.expire=System.currentTimeMillis()+this.delay;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }
        @Override
        public int compareTo(Delayed o) {
            user u=(user)o;
            return this.id-u.id;
        }
    }
}
