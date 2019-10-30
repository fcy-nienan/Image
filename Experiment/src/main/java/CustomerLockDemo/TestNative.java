package CustomerLockDemo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.util.Optional;
import sun.misc.DoubleConsts;

import java.io.File;

public class TestNative {
    public static void main(String args[]) throws InterruptedException, RunnerException {
        Options optional=new OptionsBuilder()
                .include(TestNative.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(optional).run();


    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public static void testNative(){
        long total1=0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//直接使用本地方法
            total1+= Double.doubleToLongBits(i);
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public static void testOuter(){
        long ttt=0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {//外部调用本地方法
            ttt += doubleToLongBits(i);
        }
    }
    public static long doubleToLongBits(double value) {
        long result =Double.doubleToRawLongBits(value);
        if ( ((result & DoubleConsts.EXP_BIT_MASK) ==
                DoubleConsts.EXP_BIT_MASK) &&
                (result & DoubleConsts.SIGNIF_BIT_MASK) != 0L) {
            result = 0x7ff8000000000000L;
        }
        return result;
    }
}
