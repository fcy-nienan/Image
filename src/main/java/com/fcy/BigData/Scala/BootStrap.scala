package com.fcy.BigData.Scala
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object BootStrap {
  def main(args:Array[String]):Unit={
    val context:SparkContext=sc
    val path:String="G:\\words.txt"
    val rdd=context.textFile(path)
    println(rdd.getNumPartitions)
    rdd.flatMap(_.split(" "))
      .map((_, 1)).reduceByKey(_+_)
      .collect()
      .foreach(println)
//    rdd.flatMap(e=>e.split(" ")).map((_,1)).reduceByKey(_+_).collect().foreach(println);
  }
  def flatmap1()={
    val li = List(1,2,3)
    val res = li.flatMap(x => x match {
      case 3 => List('a','b')
      case _ => List(x*2)
    })
    println(res)
  }
  def sc = {
    val sparkHome = "D:\\BigData\\Spark"
    val conf = new SparkConf().setAppName("HelloWorld").setMaster("local").setSparkHome(sparkHome)
    // setMaster指定Master
    // setSparkHome指向安装spark的地址，视环境而定
    val sparkContext:SparkContext = new SparkContext(conf)
    sparkContext
  }
}
