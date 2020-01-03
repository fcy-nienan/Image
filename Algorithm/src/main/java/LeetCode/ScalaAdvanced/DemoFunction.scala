package LeetCode.ScalaAdvanced

import org.apache.spark.{SparkConf, SparkContext}

object DemoFunction {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("wordCountLocal") //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setMaster("local") //此时程序在本地运行，无需安装Spark的任何集群
    val sc = new SparkContext(conf) //创建SparkContext对象，通过传入SparkConf实例来定制Spark运行的具体参数和配置信息
    //文件的路径，最小并行度（根据机器数量来决定）
    //val lines:RDD[String]= sc.textFile("F://spark//spark-1.6.2-bin-hadoop2.6//README.md", 1)    //读取本地文件，并设置Partition = 1
    val rdd=sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10))
    def mapFun(iter:Iterator[Int]):Iterator[Int]={
      println("map partitions")
      var res=for (e<-iter)yield e*2
      res
    }
    val rdd2=rdd.mapPartitions(mapFun)
    rdd.foreachPartition(mapFun);
    rdd2.foreach(println)

    sc.stop() //注意一定要将SparkContext的对象停止，因为SparkContext运行时会创建很多的对象


  }

}
