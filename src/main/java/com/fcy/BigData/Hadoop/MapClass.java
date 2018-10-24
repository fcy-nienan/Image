package com.fcy.BigData.Hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-19  20:10
 */
public class MapClass extends Mapper<Object,Text,Text,IntWritable> {
    private static final IntWritable writable=new IntWritable(1);
    private Text word=new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        System.out.println("在一个任务重，此方法只运行一次，用于初始化一些资源");
    }
    @Override
    protected void map(Object key,Text value,Context context)throws IOException,InterruptedException{
        System.out.println("start map");
        String line=value.toString();
        StringTokenizer tokenizer=new StringTokenizer(line);
        System.out.println("key:"+key+"---value:"+value.toString());
        while(tokenizer.hasMoreTokens()){
            String token=tokenizer.nextToken();
            word.set(token);
            context.write(word,writable);
            System.out.println(token);
        }
        System.out.println("end map");
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
        System.out.println("该方法在任务中只运行一次，用于资源的释放操作");
    }
}
