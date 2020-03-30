package Advanced.Concurrent.Thread;


import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-11  22:03
 *
 * 使用并行排序
 *
 * watch normal:2726000
 * watch parallel:15600
 */
public class ParallelStream {
    public static void main(String[] args) {
        int count=1000000;
        List<String> list=new ArrayList<>();
        fullData(list,count);
        List<String> clone=new ArrayList(list);

        StopWatch watch=new StopWatch();

        watch.start();
        list.stream().sorted();
        watch.stop();
        System.out.println("watch normal:"+watch.getNanoTime());

        watch.reset();
        watch.start();
        clone.parallelStream().sorted();
        watch.stop();
        System.out.println("watch parallel:"+watch.getNanoTime());
    }
    private static void fullData(List list,int count){
        for(int i=0;i<count;i++){
            list.add(UUID.randomUUID().toString());
        }
    }
}
