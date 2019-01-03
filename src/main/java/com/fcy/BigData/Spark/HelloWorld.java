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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-20  0:13
 */
public class HelloWorld {
    public static void main(String[] args) throws IOException, YarnException, InterruptedException, ParseException {
//        String rddFilePath="G:\\words.txt";
//        JavaSparkContext sc = Sc.sc();
////        JavaPairRDD<String,Integer> pairRDD=sc.textFile(rddFilePath)
////                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
////                .mapToPair(s->new Tuple2<String,Integer>((String) s,1))
////                .reduceByKey((x,y)->x+y);
//        sc.textFile(rddFilePath).foreach(e-> System.out.println(e));
//        System.out.println(sc.textFile(rddFilePath).getNumPartitions());
//        sc.textFile(rddFilePath).map(e->e.split(" "));
////        pairRDD.foreach(e-> System.out.println(e));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
        String start="2012-12-12";
        String end="2012-12-13";
        Date date=dateFormat.parse(start);
        Date datee=dateFormat.parse(end);
        System.out.println(date.getDay());
        System.out.println();
        System.out.println(date.getTime());
        System.out.println(datee.getTime());
        System.out.println(datee.getTime()-date.getTime());
        String s="1,2";
        s.split(",");
    }

}
