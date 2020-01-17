package LeetCode.ScalaAdvanced

import org.apache.hadoop.mapreduce.task.reduce.Shuffle
import org.apache.spark.rdd.MapPartitionsRDD
import org.apache.spark.{Partition, SparkConf, SparkContext, TaskContext}

import scala.collection.mutable

import java.net.InetAddress

import org.apache.spark.{SparkConf, SparkContext}

import scala.xml.Null

object DemoFunction {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("wordCountLocal") //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setMaster("local") //此时程序在本地运行，无需安装Spark的任何集群
    val sc = new SparkContext(conf) //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息
    //文件的路径，最小并行度（根据机器数量来决定）
    //val lines:RDD[String]= sc.textFile("F://spark//spark-1.6.2-bin-hadoop2.6//README.md", 1)    //读取本地文件，并设置Partition = 1
    val rdd1=sc.parallelize(1 to 10,3)
    val l = rdd1.map(e => (e, 1)).reduceByKey((e1, e2) => e1 + e2).count()
    println(l)
    sc.stop() //注意一定要将SparkContext的对象停止，因为SparkContext运行时会创建很多的对象
  }


//    val conf = new SparkConf()
//    conf.setAppName("wordCountLocal")
//    conf.setMaster("local")
//    val sc = new SparkContext(conf)
//    var rdd=sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10),5)
//    rdd.mapWith(e=>e*10)((a,e)=>a+e).collect().foreach(println)
//    sc.stop() //注意一定要将SparkContext的对象停止，因为SparkContext运行时会创建很多的对象
//
  def compute(base:Int):Int={
    var sum=0
    for (i<-0 to 100000){
      sum=sum+1
    }
    sum+base
  }
}
