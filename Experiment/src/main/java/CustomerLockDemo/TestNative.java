package CustomerLockDemo;

import sun.misc.DoubleConsts;

import java.io.File;
/*
* 当JVM发现某个方法或代码块运行特别频繁的时候，
* 就会认为这是“热点代码”（Hot Spot Code)。然后JIT会把部分“热点代码”编译成本地机器相关的机器码，
* 并进行优化，然后再把编译后的机器码缓存起来，以备下次使用。
* */
public class TestNative {
    public static void main(String args[]) throws InterruptedException {
        testNative();
        testNative();
        testNative();

        testOuter();
        testOuter();
        testOuter();


    }
    private static void testNative(){
        long total1=0;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//直接使用本地方法
            total1+= Double.doubleToLongBits(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");
    }
    private static void testOuter(){
        long ttt=0;
        long ttt1 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//外部调用本地方法
            ttt = doubleToLongBits(i);
        }
        long ttt2 = System.currentTimeMillis();
        System.out.println(ttt2 - ttt1 + "ms");
    }
    private static long doubleToLongBits(double value) {
        long result =Double.doubleToRawLongBits(value);
        if ( ((result & DoubleConsts.EXP_BIT_MASK) ==
                DoubleConsts.EXP_BIT_MASK) &&
                (result & DoubleConsts.SIGNIF_BIT_MASK) != 0L) {
            result = 0x7ff8000000000000L;
        }
        return result;
    }
}
