package ReflectionTest;

import com.sun.tracing.dtrace.ModuleName;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.logging.Logger;

import static org.openjdk.jmh.annotations.Mode.AverageTime;

public class MainTest {
    private static MethodBody body=new MethodBody();
    private static Logger logger = Logger.getLogger(MainTest.class.getName());
    @Benchmark
    @BenchmarkMode(AverageTime)
    public String invoke() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method clazz=MethodBody.class.getDeclaredMethod("computeUUID");
        Object result=clazz.invoke(body);
        return (String) result;
    }
    @Benchmark
    @BenchmarkMode(AverageTime)
    public String invoke1(){
        return body.computeUUID();
    }
    public static void main(String args[]) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MainTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
