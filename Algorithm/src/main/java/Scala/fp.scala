package Scala

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object fp {
  def main(args: Array[String]): Unit = {
    val intToInt = closure()
    println(intToInt(1))
    println(intToInt(2))
    var x=List(1,2,3,4)
    println(x.aggregate((1, 1))((x, y) => (x._1 + y, x._2 + 1), ((x, y) => (x._1 + y._1, x._2 + 1))))
  }
  def closure():(Int)=>Int={
    var factory=1
    val x=(x:Int)=>{
      factory+=1
      x*factory
    }
    x
  }
  def k(a:Int)(b:Int)(c:Int):String=a+b+c.toString
  def g(a: Int)(b: Int): Int = a + b
  def k(a:Int)(b:Int):Int={
    a+b
  }
  def add(x:Int):Int=>Int=>Int={
    (y:Int)=>{
      (z:Int)=>{
        x+y+z
      }
    }
  }
}
