package LeetCode.BackTracking

import scala.collection.mutable.ListBuffer
//endsWith
//indexOf
//interest
//max
//min
//mkString
//sorted
//startsWith
//sum
//toMap
//toArray
//toSeq
//toSet
//toString
//toBuffer
object DemoCollection {
  def main(args:Array[String]):Unit={
    var list:List[Int]=List(1,2,3,4,5);
    val ints = list.toSeq
    list=1 :: list

    println(ints)

  }
  def display(list:List[Int]):Unit={
    list.foreach(println)
  }
}
