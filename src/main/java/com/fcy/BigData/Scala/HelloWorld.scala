package com.fcy.BigData.Scala

import org.apache.spark.{SparkConf, SparkContext}

class HelloWorld {
  def main(args: Array[String]) {
    val cf=new SparkConf();
    val ssc=new SparkContext(cf);
    val lines=ssc.textFile("G:\\words.txt");
    println(lines.getNumPartitions);
    println("test")
    ssc.stop();
  }
}
