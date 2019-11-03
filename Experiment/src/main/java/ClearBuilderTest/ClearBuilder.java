package ClearBuilderTest;

import CommonUtil.StringUtil;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ClearBuilder {
    public static void main (String args[]) throws RunnerException {
        Options options= new OptionsBuilder()
                .include(ClearBuilder.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public static void remove(){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<100000;i++){
            builder.append(StringUtil.getRandomString(10,true));
        }
        builder.delete(0,builder.length());
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public static void setLength(){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<100000;i++){
            builder.append(StringUtil.getRandomString(10,true));
        }
        builder.setLength(0);
    }
}
