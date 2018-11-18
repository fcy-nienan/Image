package com.fcy.BigData.Scala

import org.apache.spark.{SparkConf, SparkContext}

class HelloWorld {
  def test() {
    val cf=new SparkConf();
    val ssc=new SparkContext(cf);
    val lines=ssc.textFile("G:\\words.txt");
    println(lines.getNumPartitions);
    println("test")
    ssc.stop();
    test3(_*2);

  }
  def test1(x:Int):Int= 4;
  def test2(x:Int):Int=test1(3);
  def test3(x:(Double)=>Double)=x(4);
  def test4[K]={

  }
  def test5:Int={
    val a=new A;

    return 6
  }
}
class A{
  private val xx:Int=10;
  private val m=0;
  val count=5;
  val array=new Array[Int](count);
  val x:Int=4;
  val y=7
  val z=8

}
class B extends A{
  override val count=3;
}
class C extends{
  override val count=5;
}with A{

}
class test(
  val t1:Int,
  val t2:Int,
  val t3:Int
)extends B{
}

