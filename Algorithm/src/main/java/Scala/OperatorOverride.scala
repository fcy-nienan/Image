package Scala

object OperatorOverride {
  def main(args: Array[String]): Unit = {
    val x1=new fcyInt(10)
    x1.display()
    val x2=new fcyApply[Int](Array())
    x2.display()
  }
}
//类似于C++中的运算符重载
class fcyInt(x:Int){
  def +(y:Int)=x+y
  def -(y:Int)=x-y
  def display(): Unit ={
    val x=new fcyInt(10)
    println(x-2)
    println(x+4)
  }
}
//可以直接通过数组一样的形式访问或者赋值
class fcyApply[T](array:Array[T]){
  def apply(x:Int)=array(x)
  def update(index:Int,x:T)=array(index)=x
  def display(): Unit ={
    val fcy=new fcyApply[Int](Array(1,2,3,4,5,6,7))
    println(fcy(4))
    fcy(4)=100
    println(fcy(4))
  }
}
class t{
  var x=0
//  def x:Int
//  def y:Int
}
