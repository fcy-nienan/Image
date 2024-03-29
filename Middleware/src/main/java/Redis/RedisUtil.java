package Redis;

import redis.clients.jedis.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-08  17:49
 */
public class RedisUtil {
    private static JedisPoolConfig config;
    private static JedisPool pool;
    private static int MaxIdle=4999;
    private static int MaxTotal=5000;
    private static int MinIdle=30;
    private static long MaxWaitTime=1000;
    private static long IdleAliveTime=1800000;
    private static long IdleCheckTime=30000;
    private static int IdleCheckPerNum=10;
    private static boolean testOnBorrow=false;
    private static String host="127.0.0.1";
    private static int port=6379;
    private static String password="838502";
//    连接超时
    private static int connectionTime=10000;
    static {
        config=new JedisPoolConfig();
//        设置最大空闲连接数
        config.setMaxIdle(MaxIdle);
//        设置最大连接数
        config.setMaxTotal(MaxTotal);
//        设置最小连接数
        config.setMinIdle(MinIdle);
//        设置等待可用连接的最长时间
        config.setMaxWaitMillis(MaxWaitTime);
//        设置获取链接的时候测试一下
        config.setTestOnBorrow(testOnBorrow);
//        config.setTestOnCreate(true);
//        config.setTestOnReturn(true);
//        每30秒检查空闲连接的数目是否正确
        config.setTimeBetweenEvictionRunsMillis(IdleCheckTime);
//        空闲连接的存活时间
        config.setMinEvictableIdleTimeMillis(IdleAliveTime);
//        每次空闲连接的检查数量
        config.setNumTestsPerEvictionRun(IdleCheckPerNum);
        pool=new JedisPool(config,host,port,connectionTime,password);
    }
    private Jedis getConnection(){
        Jedis jedis=null;
        boolean flag=false;
        while(!flag){
            try{
                jedis=pool.getResource();
                flag=true;
            }catch (Exception e){
                flag=false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return jedis;
    }
    private void returnConnection(Jedis jedis){
        if(jedis!=null){
            pool.returnResource(jedis);
        }
    }
    public JedisCommands getJedisProxy(){
        DynamicProxy<JedisCommands> proxy=new DynamicProxy<>(getConnection());
        return proxy.bind();
    }
    public BasicCommands getBasicProxy(){
        DynamicProxy<BasicCommands> proxy=new DynamicProxy<>(getConnection());
        return proxy.bind();
    }
    private class DynamicProxy<T> implements InvocationHandler{
        private T t;
        public DynamicProxy(T t){
            this.t=t;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result=method.invoke(t,args);
            returnConnection((Jedis)t);
            return result;
        }
        public  T bind(){
            return (T) Proxy.newProxyInstance(Jedis.class.getClassLoader(),
                    t.getClass().getInterfaces(),this);
        }
    }
    //可重入    续命    阻塞使用自旋
    public void lock(){
        long result;
        while((result=getJedisProxy().setnx("state","1"))!=1){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void unlock(){
        long result=getJedisProxy().del("state");
        if(result==1){

        }else{
            throw new UnsupportedOperationException("Operation is InValid!");
        }
    }
    private Jedis jedis= pool.getResource();
    private ThreadLocal<String> local=new ThreadLocal<>();
    private ThreadLocal<Integer> integerThreadLocal=new ThreadLocal<>();//解决锁重入
    private ThreadLocal<Thread> extension=new ThreadLocal<>();
    //程序运行    开发集成    业务 (阻塞和非阻塞(自旋))
    public boolean lock1(){
        String s1 = local.get();
        if (s1==null) {
            String s = UUID.randomUUID().toString();
            local.set(s);//锁和当前线程挂钩,避免其他线程调用unlock1方法
            String lock = jedis.set(s + "lock", "1", "", "", 30);
            integerThreadLocal.set(integerThreadLocal.get() == null ? 1 : integerThreadLocal.get() + 1);
            //锁设置了30秒,但如果业务方法是30秒以上呢?所以锁需要续命
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        jedis.set(s + "lock", "1", "", "", 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            t.start();
            extension.set(t);
        }else{
            integerThreadLocal.set(integerThreadLocal.get()==null?1:integerThreadLocal.get()+1);
            return true;
        }
        return true;
    }
    public void unlock1(){
        String s = local.get();
        if (s!=null) {
            int integer = integerThreadLocal.get();
            if (integer>1) {
                integerThreadLocal.set(integer-1);
            }else if(integer==1){
                jedis.del(s+"lock");
                integerThreadLocal.set(0);
                extension.get().interrupt();
            }
        }
    }
    public void test(){
        lock1();
        try{

            //如果在try中直接退出了那么锁就会得不到释放,所以锁需要有一个超时时间
        }finally {
            unlock1();
        }
    }
}
