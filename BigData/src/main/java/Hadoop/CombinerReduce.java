package Hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-21  22:36
 */
public class CombinerReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("start Combiner");
        System.out.println("key:"+key);
        int sum=0;
        for(IntWritable intWritable:values){
            sum+=intWritable.get();
            System.out.println(intWritable);
        }
        System.out.println("end Combiner");
        context.write(key,new IntWritable(sum));
    }
}
