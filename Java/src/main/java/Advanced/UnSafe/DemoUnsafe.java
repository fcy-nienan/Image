package Advanced.UnSafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class DemoUnsafe {
    private static Logger logger = Logger.getLogger(DemoUnsafe.class.getName());
    private static Unsafe unSafe;
    private long longValue;
    private static long longValueOffset;
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Object o = theUnsafe.get(null);
            unSafe = (Unsafe) o;
        }catch (Exception e){
            throw new Error("init failed!");
        }
    }
    public static void main(String args[]) throws Exception {

//        获取内存字段偏移地址
        longValueOffset=unSafe.objectFieldOffset(DemoUnsafe.class.getDeclaredField("longValue"));

//        动态加载类
//        unSafe.defineClass(null,new byte[1024],0,2014,null,null);

//        分配内存并且将值填充
        long address=unSafe.allocateMemory(8);
        unSafe.setMemory(address,4l,(byte)1);
        System.out.println(unSafe.getInt(address));

        System.out.println(unSafe.addressSize());
        System.out.println(unSafe.pageSize());
    }
}
