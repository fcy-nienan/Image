package com.fcy.BigData.Spark;

import com.fcy.BigData.Spark.Conf.Sc;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
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
//        JavaRDD<String> data = sc.textFile(rddFilePath);
//        // 加载README.md文件并创建RDD
//        data.foreach(new VoidFunction<String>() {
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });
        JavaPairRDD<String,Integer> pairRDD=sc.textFile(rddFilePath)
                .repartition(5)
                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(s->new Tuple2<String,Integer>((String) s,1))
                .reduceByKey((x,y)->x+y);
        System.out.println(pairRDD.getNumPartitions());
        List<Tuple2<String,Integer>> result=pairRDD.collect();
        pairRDD.cache();
    }
}
