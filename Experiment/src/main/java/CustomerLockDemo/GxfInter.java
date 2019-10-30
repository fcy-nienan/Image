package CustomerLockDemo;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/28 3:36 PM
 **/
public class GxfInter {
    private sun.misc.Unsafe U;
    private long offset;
    private Integer value = 0;
    private static Object lock = new Object();

    public GxfInter(int value) throws Exception {
        this.value = value;
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        U = (Unsafe) f.get(null);
        offset = U.objectFieldOffset(GxfInter.class.getDeclaredField("value"));
    }
    public void casIncreament(){
        boolean update = false;
        int expect=value;
        do{
            update = U.compareAndSwapObject(this, offset, value, value + 1);
//            expect=U.getIntVolatile(this,offset);
//            update=U.compareAndSwapInt(this,offset,expect,expect+1);
        }while (!update);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws Exception {
        GxfInter gxfInteger = new GxfInter(0);
        int threadNum = 12;
        Runnable add = () -> {
            for(int i = 0; i < 10000000; i++){
                gxfInteger.casIncreament();
            }
        };
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[threadNum];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(add);
        }
        for(int i = 0; i < threadNum; i++){
            threads[i].start();
        }
        for(int i = 0; i < threadNum; i++){
            threads[i].join();
        }
        System.out.println("time cost : " + (System.currentTimeMillis() - start));
//    Thread.sleep(10000);
        System.out.println("result: " + gxfInteger.getValue());
    }
}
