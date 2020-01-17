package LeetCode.ScalaAdvanced

import java.net.InetAddress

import org.apache.spark.{SparkConf, SparkContext}

import scala.xml.Null

object DemoFunction {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf()
//    conf.setAppName("wordCountLocal")
//    conf.setMaster("local")
//    val sc = new SparkContext(conf)
//    var rdd=sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10),5)
//    rdd.mapWith(e=>e*10)((a,e)=>a+e).collect().foreach(println)
//    sc.stop() //注意一定要将SparkContext的对象停止，因为SparkContext运行时会创建很多的对象
//
  }
  def compute(base:Int):Int={
    var sum=0
    for (i<-0 to 100000){
      sum=sum+1
    }
    sum+base
  }
}
