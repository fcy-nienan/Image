package LeetCode.ScalaAdvanced

import scala.annotation.tailrec

object Collection {
  class t(who:String){
    def love(x:String) = x match {
      case "you"=>println("i love you")
      case "me"=>println("i love me")
      case default=>println(default)
    }
  }
  var I="i"
  var You="you"
  implicit def conve(x:String):t=return new t(x)
  def main(args: Array[String]): Unit = {
    I love You
    "i" love "me"

  }

  def Demo():Unit={
//    谓词:一个方法、函数或者匿名函数，接受多个参数返回一个布尔值

  }
//  严格和惰性集合
//  严格集合在通过map,filter等一些转换方法的时候会立刻分配内存
//  惰性集合在上述transform的一些方法的时候不会创建元素并且不会分配

//  IndexedSeq:随机访问
//  LinearSeq:线性访问
//  Seq:
//
//  过滤方法:collect,diff,distinct,drop,dropWhile,filter,filterNot,find,foldLeft
//          foldRight,head,headOption,init,intersect,last,lastOption,reduceLeft,
//          reduceRight,slice,tail,take,takeWhile,union
//  转换方法
//  分组方法
//  数学和信息方法


  def DemoCollection():Unit={
    (1 to 10 by 1).foreach(println)
    (1 until 10 by 1).foreach(println)
    (10 to 0 by -1).foreach(println)
    (1.2 to 10.8 by 1.2).foreach(println)
    ('a' to 'g' by 3).foreach(println)

  }

  def nestedFunction():Unit={
    @tailrec
    def dfs(): Unit ={
      dfs()
    }

    dfs()
  }
  def max(implicit a:Int,b:Int)={

  }
  def min(a:Int)(implicit b:Int)={

}
}
