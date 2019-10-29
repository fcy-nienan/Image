package com.fcy.BigData.Scala

trait Interface {
  def void()
  def int(x:Int):Int=3
  def double(x:(Double)=>Double)=x(3)
  def float(y:(Float)=>Float)=y(3)+y(4)
  def byte(y:(Float)=>Float)=y(3)+y(4)
}
abstract class ex extends Interface{

}
