package Scala

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.Random

object fp {
  def main(args: Array[String]): Unit = {
    val a:List[Int] = List(1, 2, 3)
    val b:List[Int] = List(1, 2, 3)
    a.eq(b)
    println(a eq b)
    a.isEmpty

  }
  def x(x:Int)=x match{
    case 12=>12
    case 14=>12
  }
  def quickSort(list:List[Int]):List[Int]={
    if (list.isEmpty) list else quickSort(list.drop(1).filter(_<list.head)):::list.head::quickSort(list.drop(1).filter(_>list.head))
  }
  def sum(x:Int):Int={
    if (x==0)return 0
    else if (x==1)return 1;
    else sum(x-1)+x
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
