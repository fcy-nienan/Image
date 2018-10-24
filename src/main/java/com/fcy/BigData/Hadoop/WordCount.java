package com.fcy.BigData.Hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.StringTokenizer;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-19  20:19
 */
public class WordCount{
    public static void main(String[] args) throws Exception {
        MapReduce();

    }
    public static void MapReduce()throws Exception{
        Configuration conf = new Configuration();

//        conf.set("fs.default.name","hdfs://localhost:9001");
//        System.setProperty("HADOOP_USER_NAME", "root");
        String[] ioArgs = new String[] { "hdfs://localhost:9001/test/words.txt",
                "hdfs://localhost:9001/test/output" };
        String[] io=new String[]{"/test/words.txt","/test/output"};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = new Job(conf);
        job.setJarByClass(WordCount.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        job.setCombinerClass(CombinerReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
