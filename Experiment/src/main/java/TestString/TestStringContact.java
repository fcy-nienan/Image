package TestString;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @descripiton: 测试各种拼接字符串的效率
 * @author: fcy
 * @date: 2018-08-20  17:56
 */
public class TestStringContact {
    public static void main(String args[]) throws RunnerException {
        Options options= new OptionsBuilder()
                .include(TestStringContact.class.getSimpleName())
                .forks(3)
                .build();
        new Runner(options).run();
    }
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public static void testAdd(){
        String s="";
        for(int i=0;i<10000;i++){
            s+=i+"";
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public static void testConcat(){
        String s="";
        for(int i=0;i<10000;i++){
            s.concat(""+i);
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public static void testBuilder(){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<10000;i++){
            builder.append(i);
        }
    }
}
