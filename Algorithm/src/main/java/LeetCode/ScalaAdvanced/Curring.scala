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
    println(add(1,2))//3
    println(addSugar(1))//Curring$$$Lambda$3/1355531311@f5f2bb7
    println(addSugar(1)(2))//3
    println(addThree(1))
    println(addThree(1)(2))
    println(addThree(1)(2)(3))
  }
  /*正常的add函数*/
  def add(x:Int,y:Int):Int=x+y
  /*语法糖的格式*/
  def addSugar(x:Int)=(b:Int)=>{
    x+b
  }
  /*不加语法糖的格式*/
  def addNoSugar(x:Int):Int=>Int=(b:Int)=>x+b
  /*
  *
  * 下面的函数是一个接收两个参数并且返回一个函数的函数
  *
  */
  def test(x:Int,y:String):String=>List[String]=(y:String)=>List(y)
  /*
  * addThree是一个函数,该函数接收一个参数,并且返回一个B
  * B是一个函数,该函数接收两个
  * 函数C是一个接收一个参数的函数
  *
  */
  def addThree(x:Int):Int=>Int=>Int=(b:Int)=>(c:Int)=>x+b+c;

}
