package com.fcy.BigData.Hadoop;

import com.fcy.BigData.HDFS.HDFSClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
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
        System.setProperty("HADOOP_USER_NAME","root");
        String inputPath="hdfs://master:9000/input/wordcount";
        String outputPath="hdfs://master:9000/output/1";
        Configuration conf = new Configuration();
        FileSystem fs=FileSystem.get(conf);
        if (fs.exists(new Path(outputPath))){
            fs.delete(new Path(outputPath),true);
        }
        conf.set("fs.default.name","hdfs://master:9000");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.scheduler.minimum-allocation-mb","512");
        conf.set("yarn.scheduler.maximum-allocation-mb","1024");
        conf.set("yarn.app.mapreduce.am.resource.mb","200");
        conf.set("yarn.resourcemanager.address", "http://master:8032");
        conf.set("yarn.resourcemanager.scheduler.address", "master:8030");
        conf.set("yarn.application.classpath","/base/bigdata/hadoop/etc/hadoop,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/common/lib/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/common/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/hdfs/lib/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/hdfs/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/mapreduce/lib/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/mapreduce/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/yarn/lib/*,\n" +
                "\t\t\t/base/bigdata/hadoop/share/hadoop/yarn/*");
        conf.set("mapreduce.app-submission.cross-platform", "true");
        String[] ioArgs = new String[] { inputPath,outputPath};
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        Job job = Job.getInstance(conf,"WordCount");
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
