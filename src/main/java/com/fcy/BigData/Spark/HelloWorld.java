package com.fcy.BigData.Spark;

import com.fcy.BigData.Spark.Conf.Sc;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.scheduler.Stage;
import org.apache.spark.scheduler.Task;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-20  0:13
 */
public class HelloWorld {
    public static void main(String[] args) throws IOException, YarnException, InterruptedException {
        String rddFilePath="G:\\words.txt";
        JavaSparkContext sc = Sc.sc();
//        JavaPairRDD<String,Integer> pairRDD=sc.textFile(rddFilePath)
//                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
//                .mapToPair(s->new Tuple2<String,Integer>((String) s,1))
//                .reduceByKey((x,y)->x+y);
        sc.textFile(rddFilePath).foreach(e-> System.out.println(e));
        System.out.println(sc.textFile(rddFilePath).getNumPartitions());
        sc.textFile(rddFilePath).map(e->e.split(" "));
//        pairRDD.foreach(e-> System.out.println(e));

    }

}
