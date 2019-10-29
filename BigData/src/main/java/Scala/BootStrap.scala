package com.fcy.BigData.Scala
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object BootStrap {
  def main(args:Array[String]):Unit={


    var array=Array(2,4,6,67,7,4,2);
    array.filter(p=>p>3).foreach(println);
    array.filter{p=>p>3}.foreach(println);



//    val context:SparkContext=sc
//    val path:String="G:\\words.txt"
//    var data:String="2,4,5,6,7";
//    data.split(",",3).foreach(println);
//    println(data.split(",", -1))
//    data.filter(p=>p!="").map(e=>).collect().foreach(println);
//    val rdd=context.textFile(path)
//    println(rdd.getNumPartitions)
//    rdd.flatMap(_.split(" "))
//      .map((_, 1)).reduceByKey(_+_)
//      .collect()
//      .foreach(println)
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
