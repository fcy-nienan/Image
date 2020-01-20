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
    stringOutput()
  }
  def spark():Unit={
    val conf = new SparkConf()
    conf.setAppName("wordCountLocal")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1=sc.parallelize(1 to 10,3)
    val l = rdd1.map(e => (e, 1)).reduceByKey((e1, e2) => e1 + e2).count()
    sc.stop()
  }
  def stringOutput():Unit={
    //三个引号会输出其包含的所有内容(空格和table等)
    //在每行前面加一个'|'会左对齐
    //每行前面这个'|'可以定制,通过stripMargin方法
    val str=
      """
        hello world
        new line
        a new line
        fcy
      """.stripMargin
    val str1=
      """
        |hello world
        |new line
        |a new line
        |fcy
      """.stripMargin
    val str2=
      """
        #hello world
        #new line
        #a new line
        #fcy
      """.stripMargin('#')
    println(str)
    println(str1)
    println(str2)
  }
}
