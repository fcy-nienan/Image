package com.fcy.BigData.Spark.Conf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-24  20:01
 */
public class Sc {
    private static JavaSparkContext sparkContext;
    public static JavaSparkContext sc(){
        String sparkHome="D:\\BigData\\Spark";
        org.apache.spark.SparkConf conf = new org.apache.spark.SparkConf()
                .setAppName("HelloWorld")
                .setMaster("local")
                .setSparkHome(sparkHome);
        // setMaster指定Master
        // setSparkHome指向安装spark的地址，视环境而定
        sparkContext = new JavaSparkContext(conf);
        sparkContext.hadoopConfiguration();
        return sparkContext;
    }
}
