package Scala

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object fp {
  def main(args: Array[String]): Unit = {
    map()

  }
  def map():Unit={
    var x=Range(1,10,1).toList
    var y=Range(100,0,-1).toList
    println(x.combinations(2).toList)
    println(Range(1,4,1).toList.permutations)

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
