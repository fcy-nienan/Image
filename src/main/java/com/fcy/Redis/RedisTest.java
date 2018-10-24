package com.fcy.Redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-08  19:22
 */
public class RedisTest {
    private static int count=0;
    private static int threadCount=5000;
    private static int waitCount=0;
    public static void main(String args[]) throws Exception {
//        testRedis();
        test();
    }
    public static void testRedis()throws Exception{
        RedisUtil util=new RedisUtil();
        JedisCommands jedis=  util.getJedisProxy();
        jedis.set("name","fcy");
        BasicCommands commands=  util.getBasicProxy();
        commands.flushAll();
        for(int i=0;i<threadCount;i++){
            Thread t=new Thread(()->{
                util.lock();
                for(int j=0;j<5000;j++){
                    count++;
                }
                waitCount++;
                util.unlock();
            });
            t.start();
        }
        while(waitCount<threadCount){
            Thread.sleep(3000);
        }
        System.out.println(waitCount);
        System.out.println(count);
    }
    public static void test(){
        Config config=new Config();
        config.useSingleServer();
        Redisson redisson = (Redisson) Redisson.create(config);
        RLock lock=redisson.getLock("kk");
        lock.lock(1000,TimeUnit.SECONDS);
        lock.unlock();


    }

}
