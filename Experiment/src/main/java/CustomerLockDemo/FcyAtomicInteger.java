package CustomerLockDemo;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
public class FcyAtomicInteger {
    private static final Unsafe unsafe ;
    private static final long valueOffset;
    static {
        try {
            Field field=Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe= (Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset
                    (FcyAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }
    private volatile int value;
    public final int getAndIncrement() {
        Object var1=this;
        long var2=valueOffset;
        int var4=1;
        int var5;
        do {
            var5 = unsafe.getIntVolatile(var1, var2);
        } while(!unsafe.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
//        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
    public int get(){
        return value;
    }
}
