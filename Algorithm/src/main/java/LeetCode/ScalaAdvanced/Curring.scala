package LeetCode.ScalaAdvanced;

//柯里化
/**
 * 柯里化从直观上看就是将一个接收多个参数的函数改造为只接收一个参数并且返回一个函数，
 * 返回的函数接收其他参数，一直到接收最后一个参数
 *
 *
 *
 */
object Curring {
  def main(args: Array[String]): Unit = {
    println(add(1,2))
    println(add2(1)(2)(3))
  }
  def add(x:Int,y:Int):Int=x+y
  def add2(x:Int)=(b:Int)=>x+b
  def add3(x:Int):Int=>Int=(b:Int)=>x+b
  def test(x:Int,y:String):String=>List[String]=(y:String)=>List(y)
}
