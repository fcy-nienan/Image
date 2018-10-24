package com.fcy.BigData.Hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-19  20:15
 */
public class ReduceClass extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("reduce start");
        IntWritable result=new IntWritable();
        int sum=0;
        for(IntWritable intWritable:values){
            sum+=intWritable.get();
        }
        System.out.println("key:"+key+"---value:"+sum);
        result.set(sum);
        context.write(key,result);
        System.out.println("reduce end");
    }
}
